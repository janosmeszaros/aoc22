(ns aoc22.util
  (:require
   [clojure.java.io :as io]
   [clojure.string :as str]))

(defn read-file [path]
  (slurp (io/resource path)))

(defn read-file-by-line [path]
  (->> path
       read-file
       str/split-lines))
