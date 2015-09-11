(ns egg.report)

(defn report
  "Produces a report map."
  [name status stdout outputs]
  {:test name
   :status status
   :stdout stdout
   :outputs outputs})

(defn report? [report]
  (every? report [:test :status :stdout :outputs]))
