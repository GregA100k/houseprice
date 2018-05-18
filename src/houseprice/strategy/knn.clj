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
