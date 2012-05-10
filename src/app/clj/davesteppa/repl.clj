(ns davesteppa.repl
  (:use [clojure.repl]
        [clojure.java.browse :only (browse-url)]
        [davesteppa.core :only (start)]))

(defn go []
  (start))

(println)
(println "Type (go) to get started.")
(println)