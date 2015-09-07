(ns egg.core
  (:require [clojure.tools.cli :refer [parse-opts]]
            [clojure.string :as string])
  (:gen-class))

;; options

(def egg-options
  [;; First three strings describe a short-option, long-option with optional
   ;; example argument description, and a description. All three are optional
   ;; and positional.
   ["-h" "--help"]
   ["-v" "--version"]])

(def validate-options
  [["-h" "--help"]
   ["-p" "--path PATH"]])

;; commands

(defn validate! [options]
  (println options))

;; help

(defn usage [options-summary]
  (->> ["usage: egg [command] [options]"
        ""
        "Options:"
        options-summary
        ""
        "Commands are:"
        "  validate Validate an egg"
        ""
        "See 'egg <command> -h' to read about a specific command."]
       (string/join \newline)))

(defn cmd-usage [cmd options-summary]
  (->> [(str "Name: " cmd)
        ""
        "Options:"
        options-summary]
       (string/join \newline)))

(defn error-msg [errors]
  (str "The following errors occurred while parsing your command:\n\n"
       (string/join \newline errors)))

(defn exit [status msg]
  (println msg))
                                        ;(System/exit status))

;; running

(defn do-cmd! [cmd name cli-options args]
  (let [{:keys [options arguments errors summary]} (parse-opts args cli-options)]
    (cond
     (:help options) (exit 0 (cmd-usage name summary))
     errors (exit 1 (error-msg errors))
     :else (cmd options))))

(defn -main [& args]
  (let [{:keys [options arguments errors summary]} (parse-opts args egg-options :in-order true)]
    (cond
     (:help options) (exit 0 (usage summary))
     (:version options) (exit 0 "Egg version 0.1.0")
     errors (exit 1 (error-msg errors))
     :else (let [cmd-with-args (first arguments)
                 [cmd & cmd-args] (string/split cmd-with-args #" ")]
             (case cmd
               "validate" (do-cmd! validate! "validate" validate-options cmd-args)
               nil (exit 0 (usage summary))
               (exit 1 (error-msg [(str "Unrecognised command: " cmd)
                                   ""
                                   "See 'egg -h' for available commands."])))))))
