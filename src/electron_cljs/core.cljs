(ns electron-cljs.core
  (:require [cljs.nodejs :as node]))

(node/enable-util-print!)

(def app (node/require "app"))
(def BrowserWindow (node/require "browser-window"))
(def dirname (js* "__dirname"))
(def main-file (str "file://" dirname "/../index.html"))

(defn on-ready [app f]
  (.on app "ready" f))

(defn on-window-all-closed [app f]
  (.on app "window-all-closed" f))

(defn darwin? []
  "Returns true if the current process' platform is OS X."
  (= (.-platform (js* "process")) "darwin"))

(defn -main [& args]
  (on-window-all-closed app
                        #(when-not (darwin?) (.quit app)))
  (on-ready app
            (fn [] (let [main-window (atom (BrowserWindow. #js {:width 800 :height 600}))]
                     (.loadUrl @main-window main-file)
                     (.openDevTools @main-window)
                     (.on @main-window "closed" #(reset! main-window nil))))))

(set! *main-cli-fn* -main)
