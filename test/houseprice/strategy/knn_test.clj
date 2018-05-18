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
    (let [m1 {:Id 1 :k1 0 :SalePrice 100}
          m2 {:Id 2 :k1 0 :SalePrice 300}]
      (is (= 0 (distance m1 m2)))))
)

(deftest test-keys-to-compare
  (testing "remove :Id from keylist"
    (let [ks '(:k1 :Id :k2)]
      (is (not (contains? (set (keys-to-compare ks)) :Id))))))
