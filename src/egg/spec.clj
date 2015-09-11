(ns egg.spec
  (:require [clojure.edn :as edn]
            [clostache.parser :as parser]))

(defn read-spec []
  (let [spec (edn/read-string (slurp "./egg.edn"))
        pwd (-> (java.io.File. ".") .getAbsolutePath)]
    (parser/render spec {:pwd pwd})))
