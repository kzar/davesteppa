(defproject davesteppa "1.0.0-SNAPSHOT"
  :description "FIXME: write description"
  :dependencies [[org.clojure/clojure "1.3.0"]
                 [ring "1.0.0-RC1"]
                 [domina "1.0.0-SNAPSHOT"]
                 [compojure "0.6.4"]
                 [enlive "1.0.0"]
                 [org.mozilla/rhino "1.7R3"]
                 [com.google.javascript/closure-compiler "r1592"]
                 [org.clojure/google-closure-library "0.0-790"]]
  :repl-init davesteppa.repl
  :plugins [[lein-cljsbuild "0.1.8"]]
  :source-path "src/app/clj"
  :extra-classpath-dirs ["src/app/cljs"
                         "templates"]
  :cljsbuild {
    :builds [{:source-path "src/app/cljs"
              :compiler {:output-to "public/javascripts/davesteppa-dev.js"
                         :optimizations :whitespace
                         :pretty-print true}}
             {:source-path "src/app/cljs"
              :compiler {:output-to "public/javascripts/davesteppa.js"
                         :optimizations :advanced
                         :pretty-print false}}]
    :repl-launch-commands {"mac" ["open" "http://localhost:8080/"]}})