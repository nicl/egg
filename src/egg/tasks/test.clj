(ns egg.tasks.test
  (:require [egg.sh :as sh]
            [egg.spec :as spec]))

(defn test
  "Run project tests"
  [options]
  (sh/sh (:test (spec/read-spec))))
