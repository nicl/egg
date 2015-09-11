(ns egg.tasks.validate
  (:require [clj-http.client :as client]))

;; helpers

(defn my-get [uri]
  (let [options {:as :json
                 :throw-exceptions false
                 :ignore-unknown-host? true}]
    (client/get uri options)))

(defn report [name passed? expected actual]
  {:test name
   :passed passed?
   :expected expected
   :actual actual})

(defn report-all [name & tests]
  {:test name
   :passed (every? :passed tests)
   :children tests})

;; checks

(defn argo? [body]
  (report "argo" true "See https://github.com/argo-rest/spec" body))

(defn status? [status]
  (let [passed? (or (= 200 status) (<= 500 status 599))]
    (report "status" passed? ["200", "5**"] status)))

(defn eggy? [uri]
  (let [response (my-get uri)]
    (conj [] (status? (:status response)) (argo? (:body response)))))

(defn egg? [base]
  "Validate egg, reporting on any errors"
  (let [endpoints ["/gtg" "/healthcheck" "/dependencies"]
        uris (map (partial str base) endpoints)]
    (report-all "Egg?" (map eggy? uris))))
