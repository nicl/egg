(ns egg.tasks.up
  (:require [egg.sh :as sh]
            [egg.spec :as spec]
            [clojure.string :as string]))

(defn up
  "Up project (connect to a build tool typically)"
  [options arguments]
  (sh/sh (str (:up (spec/read-spec)) " " (string/join " " arguments))))
