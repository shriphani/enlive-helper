(defproject enlive-helper "0.1.0"
  :description "Enlive tools"
  :url "https://github.com/shriphani/enlive-helper"
  :license {:name "CRAPL License"
            :url "http://matt.might.net/articles/crapl/CRAPL-LICENSE.txt"}
  :dependencies [[clj-http "0.9.1"]
                 [enlive "1.1.5"]
                 [net.sourceforge.htmlcleaner/htmlcleaner "2.8"]
                 [org.clojure/clojure "1.5.1"]]
  :signing {:gpg-key "02CF1D9A"}
  :deploy-repositories [["clojars" {:creds :gpg}]])
