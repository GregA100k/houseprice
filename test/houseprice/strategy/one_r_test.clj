(ns houseprice.strategy.one-r-test
  (:require [clojure.test :refer :all]
            [houseprice.strategy.one-r :as one-r :refer :all]))


(deftest get-averages-test
  (testing "get averages by :k1 key"
    (let [rows '({:Id 1 :k1 0 :k2 "A" :k3 "5" :SalePrice "150000"}
                {:Id 2 :k1 0 :k2 "B" :k3 "4" :SalePrice "140000"}
                {:Id 3 :k1 1 :k2 "C" :k3 "5" :SalePrice "160000"})
          expected-map {:k1 '([0 145000.0] [1 160000.0])}
         ]
      (is (= expected-map (get-averages :k1 rows))))
))

(deftest get-distinct-values
  (testing "get the values for a column"
    (let [rows '({:Id 1 :k1 0 :k2 "A" :k3 "5" :SalePrice "150000"}
              {:Id 2 :k1 0 :k2 "B" :k3 "4" :SalePrice "140000"}
              {:Id 3 :k1 1 :k2 "C" :k3 "5" :SalePrice "160000"})
       ]
      (is (= '(0 1) (get-values :k1 rows)))))
  (testing "get the counts for all the values of a column"
    (let [rows '({:Id 1 :k1 0 :k2 "A" :k3 "5" :SalePrice "150000"}
            {:Id 2 :k1 0 :k2 "B" :k3 "4" :SalePrice "140000"}
            {:Id 3 :k1 1 :k2 "C" :k3 "5" :SalePrice "160000"})
         ]
      (is (= '([0 2] [1 1])) (get-value-counts :k1 rows)))
  ))
