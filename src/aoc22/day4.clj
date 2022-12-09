(ns aoc22.day4
  (:require
   [aoc22.util :as util]
   [clojure.string :as str]
   [net.cgrand.xforms :as x]))

(def input (->> "day4/input.txt"
                (util/read-file-by-line)
                (into []
                      (comp (map #(str/split % #",|-"))
                            (map (fn [v] (mapv (fn [^String s] (Integer/parseInt s)) v)))))))

(defn fully-contains? [ff fs sf ss]
  (and (<= ff sf)
       (>= fs ss)))

(defn has-duplication? [[ff fs sf ss]]
  (or (fully-contains? ff fs sf ss)
      (fully-contains? sf ss ff fs)))

(defn overlap? [ff fs sf _ss]
  (<= ff sf fs))

(defn has-overlap? [[ff fs sf ss]]
  (or
    (overlap? ff fs sf ss)
    (overlap? sf ss ff fs)))

(defn part-1 []
  (x/count
   (filter has-duplication?)
   input))

(defn part-2 []
  (x/count
   (filter has-overlap?)
   input))
