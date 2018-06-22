(ns houseprice.strategy.knn-test
  (:require [clojure.test :refer :all]
            [houseprice.strategy.knn :as knn :refer :all]))

(deftest distance-to-same
  (testing "distance between matching empty objects should be 0"
    (let [m {}]
      (is (= 0 (distance m m)))))
  (testing "distance between single matching key"
    (let [m {:k1 0}]
      (is (= 0 (distance m m)))))
)
(deftest distance-to-different
  (testing "distance between non-matching single key"
    (let [m1 {:k1 0}
          m2 {:k1 1}]
      (is (not= 0 (distance m1 m2)))))
  (testing "distance between two keys"
    (let [m1 {:k1 0 :k2 0}
          m2 {:k1 0 :k2 1}]
      (is (not= 0 (distance m1 m2)))))
  (testing "distance is greater with more non-matching columns"
    (let [m1 {:k1 0 :k2 0}
          m2 {:k1 0 :k2 1}
          m3 {:k1 1 :k2 1}]
      (is (> (distance m1 m3) (distance m1 m2)))))
)

(deftest special-columns
  (testing "Id and SalePrice are not compared"
    (let [m1 {:Id 1 :k1 0 :SalePrice "100"}
          m2 {:Id 2 :k1 0 :SalePrice "300"}]
      (is (= 0 (distance m1 m2)))))
)

(deftest test-keys-to-compare
  (testing "remove :Id from keylist"
    (let [ks '(:k1 :Id :k2)]
      (is (not (contains? (set (keys-to-compare ks)) :Id))))))

(deftest find-closest-neighbor
  (testing "exact match"
    (let [rows '({:Id 1 :k1 0 :k2 "A" :k3 "5" :SalePrice "150000"}
                 {:Id 2 :k1 0 :k2 "B" :k3 "4" :SalePrice "140000"}
                 {:Id 3 :k1 1 :k2 "C" :k3 "5" :SalePrice "180000"})
          testrow {:Id 5 :k1 0 :k2 "B" :k3 "4" :SalePrice "200000"}]
      (is (= 2 (:Id (first (find-nearest 1 testrow rows)))))))
)

(deftest find-predicted-price
  (testing "average of nearest neighbor"
    (let [rows '({:Id 1 :k1 0 :k2 "A" :k3 "5" :SalePrice "150000"}
                 {:Id 2 :k1 0 :k2 "B" :k3 "4" :SalePrice "140000"}
                 {:Id 3 :k1 1 :k2 "C" :k3 "5" :SalePrice "180000"})
          testrow {:Id 5 :k1 0 :k2 "B" :k3 "4"}]
      (is (= (float 140000) (predict-price 1 testrow rows)) )))
  (testing "average of nearest neighbor"
    (let [rows '({:Id 1 :k1 0 :k2 "A" :k3 "5" :SalePrice "150000"}
                 {:Id 2 :k1 0 :k2 "B" :k3 "4" :SalePrice "140000"}
                 {:Id 3 :k1 1 :k2 "B" :k3 "4" :SalePrice "180000"})
          testrow {:Id 5 :k1 0 :k2 "B" :k3 "4"}]
      (is (= (float 160000) (predict-price 2 testrow rows)) )))
)

(deftest calc-average-price
  (testing "average of one"
    (let [rows '({:Id 1 :k1 0 :k2 "A" :k3 "5" :SalePrice "150000"}
                 {:Id 2 :k1 0 :k2 "B" :k3 "4" :SalePrice "140000"}
                 {:Id 3 :k1 1 :k2 "C" :k3 "5" :SalePrice "160000"})]
      (is (= (float 150000) (average-price rows)))))
)

(deftest build-test-function
  (testing "building the test function"
    (let [rows '({:Id 1 :k1 0 :k2 "A" :k3 "5" :SalePrice "150000"}
                 {:Id 2 :k1 0 :k2 "B" :k3 "4" :SalePrice "140000"}
                 {:Id 3 :k1 1 :k2 "B" :k3 "4" :SalePrice "180000"})
          test-function (test-row-function rows 2)
          testrow {:Id 5 :k1 0 :k2 "B" :k3 "4"}
          expected-test-file-row (str "5," (str (float 160000)))
         ]
      (is (= expected-test-file-row (test-function testrow)))
    ))
)

(deftest column-compare-test
  (testing "default comparison"
    (let [rows '({:Id 1 :k1 0 :k2 "A" :k3 "5" :SalePrice "150000"}
                 {:Id 2 :k1 0 :k2 "B" :k3 "4" :SalePrice "140000"}
                 {:Id 3 :k1 1 :k2 "B" :k3 "4" :SalePrice "180000"})
         ]
      (is (= 2 (distance (first rows) (second rows)))))
  )
  (testing "default OverallQual comparison"
    (let [rows '({:Id 1 :OverallQual "5" :SalePrice "150000"}
                 {:Id 3 :OverallQual "3" :SalePrice "180000"})
         ]
      (is (= 1/5 (column-compare :OverallQual (first rows) (second rows))))
      (is (= 2/10 (distance (first rows) (second rows)))))
  ))
