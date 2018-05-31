(ns houseprice.strategy.default)



(defn process-row
  [l]
  (str (:Id l) "," 150000.00))
