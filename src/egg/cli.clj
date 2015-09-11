(ns egg.cli
  (:require [clojure.string :as string]))

(defn usage [options-summary]
  (->> ["usage: egg [command] [options]"
        ""
        "Options:"
        options-summary
        ""
        "Commands are:"
        ""
        "build      Run the build command, producing a Dockerfile"
        "up         Run arbitary commands (typically against the language build-tool)"
        "validate   Validate an egg meets service requirements"
        "           (https://github.com/guardian/service)"
        ""
        "See 'egg <command> -h' to read about a specific command."]
       (string/join \newline)))

(defn task-usage [cmd options-summary]
  (->> [(str "Name: " cmd)
        ""
        "Options:"
        options-summary]
       (string/join \newline)))

(defn error-msg [errors]
  (str "The following errors occurred while parsing your command:\n\n"
       (string/join \newline errors)))

;;(System/exit status))
