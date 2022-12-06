(ns aoc22.day2
  (:require
    [aoc22.util :as util]
    [clojure.string :as str]))

(def extra-score {:rock     1
                  :paper    2
                  :scissors 3})

(def beat-mapping {:rock     :scissors
                   :paper    :rock
                   :scissors :paper})

(def lose-mapping (zipmap (vals beat-mapping)
                          (keys beat-mapping)))

(defn transform-part-1 [v]
  (condp contains? v
    #{"A" "X"} :rock
    #{"B" "Y"} :paper
    #{"C" "Z"} :scissors
    (throw (ex-info "Unexpected value" {}))))

(defn transform-second-column [f v]
  (condp = v
    "X" (beat-mapping f)
    "Y" f
    "Z" (lose-mapping f)
    (throw (ex-info "Unexpected value" {}))))

(defn calculate-score [player-1 player-2]
  (cond
    (= player-1 player-2) 3
    (= (beat-mapping player-2) player-1) 6
    :else 0))

(def input (into []
                 (map #(str/split % #" "))
                 (str/split-lines (util/read-file "day2/input.txt"))))

(def score-calulation-xf
  (map (fn [[a b]]
         (+ (calculate-score a b)
            (extra-score b)))))

(defn part-1 []
  (transduce (comp
              (map #(mapv transform-part-1 %))
              score-calulation-xf)
             +
             input))

(defn part-2 []
  (transduce (comp
              (map (fn [[a b]]
                     (let [transformed-a (transform-part-1 a)]
                       [transformed-a (transform-second-column transformed-a b)])))
              score-calulation-xf)
             +
             input))
