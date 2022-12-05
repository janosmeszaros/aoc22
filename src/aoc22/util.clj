(ns aoc22.util
  (:require [clojure.java.io :as io]))

(defn read-file [path]
  (slurp (io/resource path)))
