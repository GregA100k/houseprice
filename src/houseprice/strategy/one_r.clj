(ns houseprice.strategy.one-r)

(defn average [coll] (apply / (reduce (fn [[sum n] x] [(+ sum x) (inc n)]) [0 0] coll)))

(defn get-averages
  "Returns a map with one key, the column name, and the value which is a list
   of vectors containing the column value and the average price for the houses
   that have that value"
  [column alldata]
  (let [bag (group-by column alldata)
       tempf (fn [x] [x (* 1.0 (average (map (fn [s] (read-string (:SalePrice s))) (get bag x))))])
      ]
   (assoc {} column (map tempf (keys bag)))
     ))

(defn get-values
  "return a list of all the values for column"
  [column alldata]
  (keys (group-by column alldata))
)

(defn get-value-counts
  "return a list of tuples.  Each tuple is a value for columns
   along with the count of the rows that contain that value"
  [column alldata]
  (let [groups (group-by column alldata)]
    (reduce (fn [l i] (conj l [i (count (get groups i))])) '() (keys groups))))

(defn keys-to-check
  "Only want to process the data keys.  Want to skip the ID and SalePrice columns"
  [all-keys & exceptions]
   (let [exception-set (set exceptions)]
     (filter #(not (contains? exception-set %)) all-keys)))

(defn predict-price [column column-value averages]
  (second (first (filter #(= column-value (first %)) (column averages)))))

(defn is-good-prediction [r col columnAvs]
  (let [actual (read-string (:SalePrice r))
        predicted (predict-price col (col r) columnAvs)
       ]
    (if (< (Math/abs (- actual predicted)) 5000)
      true
      false)))

(defn find-predictor-success-rate
  "for the given column, go through all the rows in the data collection and See
   how many of them are accurately predicted.  return the percentage"
  [col avgs data]
  (let [[truecount falsecount] (reduce (fn [[trues falses] row]  (if (is-good-prediction row col avgs)
                                       [(inc trues) falses]
                                       [trues (inc falses)])) [1 1] data)
       ]
       [col (/ truecount falsecount)]))

(defn check-by-column [column alldata]
  (let [averages (get-averages column alldata)]
    (find-predictor-success-rate column averages alldata)))

(defn check-em-all [alldata attributes]
  (let [process-keys (keys-to-check attributes :Id :SalePrice)
        rules (reduce (fn [l i] (conj l (check-by-column i alldata))) [] process-keys)
      ]
      (sort-by second rules)))



(defn predict-price-2 [averages column column-value]
  (predict-price column column-value averages))

(defn predict-price-for-column [averages column]
  (partial predict-price-2 averages column))

(defn test-for-column [f r]
  (str (:Id r) "," (f (:OverallQual r))))

(defn test-function [f]
  (partial test-for-column f))

(defn test-row-function
  "returns a function with a row of data parameter
   which predicts the price for that row and
   returns a string with the id and price separated by a comma"
  [data]
  (test-function (predict-price-for-column (get-averages :OverallQual data) :OverallQual)))
