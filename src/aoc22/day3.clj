(ns aoc22.day3
  (:require
   [aoc22.util :as util]
   [clojure.string :as str]
   [net.cgrand.xforms :as x]))

(def input (-> "day3/input.txt"
               util/read-file
               str/split-lines))

(defn ->priority [s]
  (cond
    (re-matches #"[a-z]" (str s)) (- (int s) 96)
    :else (- (int s) 38)))

(defn find-duplicates [s]
  (let [[first-compartment second-compartment] (mapv set (split-at (/ (count s) 2) s))]
    (reduce (fn [a v]
              (if (contains? second-compartment v)
                (reduced (->priority v))
                a))
            0
            first-compartment)))

(defn part-1 []
  (transduce (map find-duplicates) + input))

(defn part-2 []
  (transduce (comp (x/partition 3
                                (x/into []))
                   (map (fn [[f s t]]
                          (reduce (fn [a v]
                                    (if (and
                                         (str/includes? s (str v))
                                         (str/includes? t (str v)))
                                      (reduced (->priority v))
                                      a))
                                  0
                                  f))))

             +
             input))
