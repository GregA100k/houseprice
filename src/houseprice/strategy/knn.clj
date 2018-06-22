(ns houseprice.strategy.knn)

(defn keys-to-compare [keylist]
  (filter #(and (not= :Id %)
                (not= :SalePrice %)) keylist))
(defmulti column-compare (fn [column m1 m2] column))
(defmethod column-compare :default [k m1 m2]
                 (if (= (get m1 k) (get m2 k)) 0 1 ))
(defmethod column-compare :OverallQual [k m1 m2]
  (/ (Math/abs (- (read-string (get m1 k)) (read-string (get m2 k)))) 10))
(defmethod column-compare :LotArea [k m1 m2]
  (/ (Math/abs (- (read-string (get m1 k)) (read-string (get m2 k)))) 210000))

(defmethod column-compare :BsmtFinSF1 [k m1 m2]
  (let [val1 (if (= "NA" (get m1 k)) 0 (read-string (get m1 k)))
        val2 (if (= "NA" (get m2 k)) 0 (read-string (get m2 k)))
       ]
  (/ (Math/abs (- val1 val2)) 5644)))

  (defmethod column-compare :BsmtUnfSF [k m1 m2]
    (let [val1 (if (= "NA" (get m1 k)) 0 (read-string (get m1 k)))
          val2 (if (= "NA" (get m2 k)) 0 (read-string (get m2 k)))
         ]
    (/ (Math/abs (- val1 val2)) 2336)))

  (defmethod column-compare :BsmtFinSF2 [k m1 m2]
    (let [val1 (if (= "NA" (get m1 k)) 0 (read-string (get m1 k)))
          val2 (if (= "NA" (get m2 k)) 0 (read-string (get m2 k)))
         ]
    (/ (Math/abs (- val1 val2)) 1474)))

(defmethod column-compare :TotalBsmtSF [k m1 m2]
    (let [val1 (if (= "NA" (get m1 k)) 0 (read-string (get m1 k)))
          val2 (if (= "NA" (get m2 k)) 0 (read-string (get m2 k)))
         ]
    (/ (Math/abs (- val1 val2)) 6110)))

  (defmethod column-compare :1stFlrSF [k m1 m2]
    (/ (Math/abs (- (read-string (get m1 k)) (read-string (get m2 k)))) (- 4692 334)))

  (defmethod column-compare :2ndFlrSF [k m1 m2]
    (/ (Math/abs (- (read-string (get m1 k)) (read-string (get m2 k)))) 2065))

  (defmethod column-compare :LowQualFinSF [k m1 m2]
    (/ (Math/abs (- (read-string (get m1 k)) (read-string (get m2 k)))) 572))

  (defmethod column-compare :GrLivArea [k m1 m2]
    (/ (Math/abs (- (read-string (get m1 k)) (read-string (get m2 k)))) (- 5642 334)))
  ; 0 1 2 3
  (defmethod column-compare :BsmtFullBath [k m1 m2]
    (let [val1 (if (= "NA" (get m1 k)) 0 (read-string (get m1 k)))
          val2 (if (= "NA" (get m2 k)) 0 (read-string (get m2 k)))
         ]
    (/ (Math/abs (- val1 val2)) 3)))
  ; 0 1 2
  (defmethod column-compare :BsmtHalfBath [k m1 m2]
    (let [val1 (if (= "NA" (get m1 k)) 0 (read-string (get m1 k)))
          val2 (if (= "NA" (get m2 k)) 0 (read-string (get m2 k)))
         ]
    (/ (Math/abs (- val1 val2)) 2)))
  ; 0 1 2 3
  (defmethod column-compare :FullBath [k m1 m2]
    (/ (Math/abs (- (read-string (get m1 k)) (read-string (get m2 k)))) 3))
  ; 0 1 2
  (defmethod column-compare :HalfBath [k m1 m2]
    (/ (Math/abs (- (read-string (get m1 k)) (read-string (get m2 k)))) 2))
  ; 0-8
  (defmethod column-compare :BedroomAbvGr [k m1 m2]
    (/ (Math/abs (- (read-string (get m1 k)) (read-string (get m2 k)))) 8))
  ; 0 1 2 3
  (defmethod column-compare :KitchenAbvGr [k m1 m2]
    (/ (Math/abs (- (read-string (get m1 k)) (read-string (get m2 k)))) 3))

  (defmethod column-compare :TotRmsAbvGrd [k m1 m2]
    (/ (Math/abs (- (read-string (get m1 k)) (read-string (get m2 k)))) (- 14 2)))

  (defmethod column-compare :Fireplaces [k m1 m2]
    (/ (Math/abs (- (read-string (get m1 k)) (read-string (get m2 k)))) 3))

  (defmethod column-compare :GarageCars [k m1 m2]
    (let [val1 (if (= "NA" (get m1 k)) 0 (read-string (get m1 k)))
          val2 (if (= "NA" (get m2 k)) 0 (read-string (get m2 k)))
         ]
    (/ (Math/abs (- val1 val2)) 4)))

  (defmethod column-compare :GarageArea [k m1 m2]
    (let [val1 (if (= "NA" (get m1 k)) 0 (read-string (get m1 k)))
          val2 (if (= "NA" (get m2 k)) 0 (read-string (get m2 k)))
         ]
    (/ (Math/abs (- val1 val2)) 1418)))

  (defmethod column-compare :WoodDeckSF [k m1 m2]
    (/ (Math/abs (- (read-string (get m1 k)) (read-string (get m2 k)))) 857))

  (defmethod column-compare :OpenPorchSF [k m1 m2]
    (/ (Math/abs (- (read-string (get m1 k)) (read-string (get m2 k)))) 547))

  (defmethod column-compare :EnclosedPorch [k m1 m2]
    (/ (Math/abs (- (read-string (get m1 k)) (read-string (get m2 k)))) 552))

  (defmethod column-compare :3SsnPorch [k m1 m2]
    (/ (Math/abs (- (read-string (get m1 k)) (read-string (get m2 k)))) 508))

  (defmethod column-compare :ScreenPorch [k m1 m2]
    (/ (Math/abs (- (read-string (get m1 k)) (read-string (get m2 k)))) 480))

  (defmethod column-compare :PoolArea [k m1 m2]
    (/ (Math/abs (- (read-string (get m1 k)) (read-string (get m2 k)))) 738))

  (defmethod column-compare :YrSold [k m1 m2]
    ; 2006 2010
    (/ (Math/abs (- (read-string (get m1 k)) (read-string (get m2 k)))) 4))

  (defmethod column-compare :YearBuilt [k m1 m2]
    (/ (Math/abs (- (read-string (get m1 k)) (read-string (get m2 k)))) (- 2010 1872)))




(defn distance [m1 m2]
  (let [all-keys (keys m1)
        compare-keys (keys-to-compare all-keys)
       ]
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
  [data & k]
  (let [k-value (if k (first k) test-k)]
  (fn [row] (str (:Id row) "," (predict-price k-value row data))))
)
