(ns houseprice.strategy.default)



(defn process-row
  "split the ID from the beginning of the comma separated input row and return that ID along with a default price"
  [l]
  (let [parts (.split l ",")]
    (str (first parts) "," 150000.00)))
