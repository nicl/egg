(ns egg.spec
  (:require [clojure.edn :as edn]
            [clostache.parser :as parser]))

(defn read-spec []
  (let [spec (slurp "./egg.edn")
        pwd (-> (java.io.File. ".") .getAbsolutePath)]
    (-> spec
        (parser/render {:pwd pwd})
        (edn/read-string))))
