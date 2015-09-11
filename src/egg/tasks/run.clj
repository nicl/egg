(ns egg.tasks.run
  (:require [egg.sh :as sh]
            [egg.spec :as spec]))

(defn run
  "Run project"
  [options]
  (sh/sh (:run (spec/read-spec))))
