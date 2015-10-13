(defproject electron-cljs "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/clojurescript "1.7.48"]]
  :node-dependencies [[source-map-support "0.3.2"]]
  :plugins [[lein-cljsbuild "1.1.0"]
            [lein-npm "0.6.1"]]
  :source-paths ["src"]
  :clean-targets ["server" "server/app.js"]
  :cljsbuild {:builds [{:id "app"
                        :source-paths ["src"]
                        :compiler {:main booker-cljs.core
                                   :output-to "server/app.js"
                                   :output-dir "server"
                                   :optimizations :simple
                                   :target :nodejs
                                   :cache-analysis true
                                   :source-map "server/app.js.map"}}]})
