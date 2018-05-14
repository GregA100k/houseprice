(ns houseprice.runner
  (require [clojure.java.io :as io]
           [houseprice.strategy.default :as test]
           [houseprice.train :as train]
           [houseprice.strategy.one-r :as one-r]
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
    ;(process-test-file "data/test.csv" "data/test_output.csv" test/process-row)
    (let [process-training-data (train/read-training-file "data/train.csv")
          data (:data (:data @train/trainatom))
          f (one-r/test-function (one-r/predict-price-for-column (one-r/get-averages :OverallQual data) :OverallQual))
         ]
      (process-test-file "data/test.csv" "data/one-r_output.csv" f)
    ))
    (do (train/read-training-file "data/train.csv")
        (println (one-r/check-em-all (:data (:data @train/trainatom)) (:headers (:data @train/trainatom))))
    ))
