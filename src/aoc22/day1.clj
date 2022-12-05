(ns aoc22.day1
  (:require [aoc22.util :as util]
            [clojure.string :as str]
            [net.cgrand.xforms :as x]))

(def xf (comp
         (map #(when-not (str/blank? %)
                 (Integer/parseInt %)))
         (partition-by number?)
         (remove #(= % [nil]))
         (map (partial reduce +))))

(def input (-> "day1/input.txt"
               (util/read-file)
               (str/split #"\n")))

(defn part-1
  [_]
  (transduce xf max 0 input))

(defn part-2
  [_]
  (transduce
   (comp xf
         (x/sort)
         (x/take-last 3))
   +
   input))
