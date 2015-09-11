(ns egg.sh
  (:require [clojure.java.shell :as shell]
            [clojure.string :as string]))

(defn errors? [& output]
  (filter #(seq (:err %)) output))

(defn sh
  "Execute a single shell command"
  [cmd]
  (println cmd)
  (let [out (apply shell/sh (string/split cmd #" "))]
    (println out)
    out))

(defn sh-all
  "Execute one or more shell commands"
  [commands]
  (println commands)
  (doall (map sh commands)))
