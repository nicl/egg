(ns egg.core
  (:require [clojure.tools.cli :refer [parse-opts]]
            [clojure.string :as string]
            [egg.tasks.build :as build]
            [egg.tasks.test :as test]
            [egg.cli :as cli])
  (:gen-class))

(def tasks
  {:egg      {:name "egg"
              :desc "The main task"
              :opts [["-h" "--help"] ["-v" "--version"]]}
   :build    {:name "build"
              :desc "Build Docker container for Egg"
              :opts [["-h" "--help"]]}
   :test     {:name "test"
              :desc "Run tests for Egg"
              :opts [["-h" "--help"]]}
   :validate {:name "validate"
              :desc "Validate Egg (against a provided URI)"
              :opts [["-h" "--help"] ["-u" "--uri URI"]]}})
;; TODO add: status, cost, new, etc.

;; should probably accept a report and print it using a moustache template
(defn exit [status msg]
  (println msg)
  (System/exit status))

(defn split-words [args]
  (if args (string/split args #" ")))

(defn do-task [f info args]
  (let [{:keys [name opts]} info
        {:keys [options arguments errors summary]} (parse-opts args opts)]
    (cond
     (:help options) (exit 0 (cli/task-usage name summary))
     errors (exit 1 (cli/error-msg errors))
     :else (f options))))

(defn match-task [args]
  (let [[task & args] (split-words (first args))]
    (case task
      "build" (do-task build/build (:build tasks) args)
      "test" (do-task test/test (:test tasks) args)
      nil (exit 0 (-> tasks :egg :options cli/usage))
      (exit 1 (cli/error-msg [(str "Unrecognised task: " task)
                          ""
                          "See 'egg -h' for available task."])))))

(defn -main [& args]
  (let [{:keys [options arguments errors summary]} (parse-opts args (-> tasks :egg :opts) :in-order true)]
    (cond
     (:help options) (exit 0 (cli/usage summary))
     (:version options) (exit 0 "Egg version 0.1.0")
     errors (exit 1 (cli/error-msg errors))
     :else (match-task arguments))))

;; e.g. (-main "build -h")
