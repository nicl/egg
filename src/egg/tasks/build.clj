(ns egg.tasks.build
  (:require [clostache.parser :as parser]
            [egg.sh :as sh]
            [egg.spec :as spec]))

(defn jar [spec]
  (println "building jar...")
  (let [cmd (get-in spec [:build :cmd])]
    (sh/sh cmd)))

(defn dockerfile [spec]
  (println "building Dockerfile...")
  (let [target (get-in spec [:build :target])
        dockerfile (parser/render-resource "Dockerfile.moustache" {:target target})]
    (println dockerfile)
    (spit "Dockerfile" dockerfile)))

(defn build
  "Construct Dockerfile for project"
  [options]
  (doto (spec/read-spec)
    (jar)
    (dockerfile)))
