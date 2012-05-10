(ns davesteppa.core
  (:require [goog.uri.utils :as uri]
            [clojure.browser.repl :as repl]
            [davesteppa.player :as player]))

(defn- server []
  (let [location (.toString window.location ())]
    (str (uri/getScheme location) "://" (uri/getDomain location))))

(defn ^:export repl []
  (repl/connect (str (server) ":9000/repl")))

(defn ^:export start []
  (.log js/console (str "Application started: " (server))))