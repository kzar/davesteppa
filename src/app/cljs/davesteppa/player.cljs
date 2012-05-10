(ns davesteppa.player
  (:use [domina :only (value text by-id set-text! append!)]
        [domina.xpath :only (xpath)])
  (:require [clojure.string :as string]))

(def sounds
     {"s" {:name "Djembe Slap" :sound (js/Audio. "samples/DjemSlap1Y.wav")}
      "t" {:name "Djembe Tone" :sound (js/Audio. "samples/DjemTone1Y.wav")}
      "b" {:name "Djembe Bass" :sound (js/Audio. "samples/DjemBass1Y.wav")}
      "m" {:name "Djembe Muted Slap" :sound (js/Audio. "samples/DjemSlapMuff1.wav")}
      "S" {:name "Djembe Slap 2" :sound (js/Audio. "samples/D1_V.wav")}
      "T" {:name "Djembe Tone 2" :sound (js/Audio. "samples/D1_U.wav")}
      "B" {:name "Djembe Bass 2" :sound (js/Audio. "samples/D1_O.wav")}
      "M" {:name "Djembe Muted Slap 2" :sound (js/Audio. "samples/DjemSlapMuff2.wav")}
      "!" {:name "Cowbell High" :sound (js/Audio. "samples/CowBellHi1.wav")}
      "l" {:name "Cowbell Low" :sound (js/Audio. "samples/CowBellLo1.wav")}
      "k" {:name "Kenkeni Open" :sound (js/Audio. "samples/kendrum.wav")}
      "K" {:name "Kenkeni Closed" :sound (js/Audio. "samples/kenclose.wav")}
      "g" {:name "Sangban Open" :sound (js/Audio. "samples/sangopen1.wav")}
      "G" {:name "Sangban Closed" :sound (js/Audio. "samples/sangclose1.wav")}
      "d" {:name "Dun Open" :sound (js/Audio. "samples/DounDoun1.wav")}
      "D" {:name "Dun Closed" :sound (js/Audio. "samples/dundunba_m_ferme.wav")}})

(def player (atom nil))
(def position (atom 0))
(def tempo (atom 300))
(def music (atom []))

(defn start-player []
  (when-not @player
    (update-music)
    (update-tempo)
    (reset! position 0)
    (reset! player (js/setInterval
                     (fn []
                       (play-step @position @music)
                       (swap! position inc))
                     @tempo))))

(defn stop-player []
  (js/clearInterval @player)
  (reset! player nil))

(defn play-beat [position music-track]
  (when-let [sound (:sound (sounds (nth music-track (rem position (count music-track)))))]
    (.play sound)))

(defn play-step [position music]
  (.log js/console (str "Position: " position))
  (dorun (map (partial play-beat position) music)))

(defn ^:export update-music []
  (reset! music (-> (by-id "music-input") value string/split-lines)))

(defn ^:export update-tempo []
  (reset! tempo (-> (by-id "tempo-input") value js/parseInt)))

(defn render-key [sounds]
  (apply str (map #(str "<li>" (first %) " - <b>" (-> % second :name) "</b></li>") sounds)))

(defn ^:export display-key []
  (append! (by-id "music-key-list") (render-key sounds)))

(defn ^:export toggle-player []
  (if @player (stop-player) (start-player)))

(defn ^:export start []
  (display-key)
  (set! (.-onclick (by-id "start-stop-button")) toggle-player)
  (set! (.-onkeyup (by-id "music-input")) update-music)
  (set! (.-onkeyup (by-id "tempo-input")) update-tempo))

(set! (.-onload js/window) start)

;(def form "//div[@id='form']")
;(play "ttss--ssttssb-ss" 300)
;(play "s--ss-tt" 300)

;(play "b-tt--s-" 300)
;(play "!-l-!!--l--l-l!l" 300)
;(play "l-!-l-l!" 300)
;(play "!--l-" 300)
;(play "-dD" 300)

;(let [music (string/replace music "|" "")]
;(play "|X--X|X-XX|-X-X|-XX-|")