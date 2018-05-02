(ns houseprice.runner
  (require [clojure.java.io :as io]
           [houseprice.strategy.default :as test]
           [houseprice.train :as train]
           ))

(defn process-test-file [infilename outfilename input-function]
  (with-open [rdr (io/reader infilename)
              wrt (io/writer outfilename)]
    (let [write-header (.write wrt "Id,SalePrice\r\n")]
    (doseq [line (drop 1 (line-seq rdr))]
      (.write wrt (str (input-function line) "\r\n")))))
)


(defn -main [args]
  (if (= "test" args)
    (process-test-file "data/test.csv" "data/test_output.csv" test/process-row)
    (train/read-training-file "data/train.csv")
    )
)
