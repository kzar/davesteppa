(ns davesteppa.core
  (:use [ring.adapter.jetty :only (run-jetty)]
        [ring.middleware.file :only (wrap-file)]
        [ring.middleware.file-info :only (wrap-file-info)]
        [ring.middleware.params :only (wrap-params)]
        [ring.middleware.reload :only (wrap-reload)]
        [ring.middleware.stacktrace :only (wrap-stacktrace)]
        [compojure.core :only (defroutes GET POST ANY)])
  (:require [net.cgrand.enlive-html :as html])
  (:import java.io.File))

(html/deftemplate layout "application.html" [content]
  [:div#content] (html/html-content content))

(defn html-response [content]
  {:status 200
   :headers {"Content-Type" "text/html; charset=utf-8"}
   :body (layout content)})

(defroutes app-routes
  (GET "/" request (html-response (slurp "templates/index.html"))))

(def app (-> app-routes
             (wrap-file "public")
             wrap-file-info
             wrap-params
             wrap-stacktrace
             (wrap-reload {:dirs ["src" "templates"]})))

(defonce server
  (run-jetty #'app {:join? false :port 8080}))

(defn start []
  (.start server))