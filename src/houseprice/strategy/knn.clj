(ns houseprice.strategy.knn)

(defn keys-to-compare [keylist]
  (filter #(and (not= :Id %)
                (not= :SalePrice %)) keylist))

(defn distance [m1 m2]
  (let [all-keys (keys m1)
        compare-keys (keys-to-compare all-keys)
        column-compare (fn [k m1 m2]
                         (if (= (get m1 k) (get m2 k)) 0 1 ))]
    (reduce + (map #(column-compare % m1 m2) compare-keys))))

(defn find-nearest [k testrow rows]
  (let [f (fn [l i] (conj l [(distance testrow i) i]))
        distance-vector (reduce f [] rows)]
    (map second (take k (sort-by first distance-vector)))))
(defn average-price
  [rows]
  (/ (apply + (map #(read-string (:SalePrice %)) rows)) (float (count rows))))

(defn predict-price
  [k testrow rows]
  (let [neighbors (find-nearest k testrow rows)]
    (average-price neighbors)))

(def test-k 5)

(defn test-row-function
  "returns a function with a row of data parameter
   which predicts the price for that row and
   returns a string with the id and price separated by a comma"
  [data]
  (fn [row] (str (:Id row) "," (predict-price test-k row data)))
)
