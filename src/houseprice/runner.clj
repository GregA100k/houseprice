(ns houseprice.runner
  (require [clojure.java.io :as io]
           [houseprice.strategy.default :as default]
           [houseprice.train :as train]
           [houseprice.strategy.one-r :as one-r]
           [houseprice.strategy.knn :as knn]
           ))

(defn process-test-file [infilename outfilename input-function]
  (with-open [wrt (io/writer outfilename)]
    (let [write-header (.write wrt "Id,SalePrice\r\n")
          in-atom (train/read-test-file infilename)
          test-data (:data (:data @train/testatom))
          ]
    (doall (map #(.write wrt (str (input-function %) "\r\n")) test-data)))))

(defn -main [& args]
  (if (= "test" (first args))
    ;(process-test-file "data/test.csv" "data/test_output.csv" test/process-row)
    (let [process-training-data (train/read-training-file "data/train.csv")
          data (:data (:data @train/trainatom))
        ;  test-row-function default/process-row
        ;  test-row-function (one-r/test-row-function data)
          test-row-function (knn/test-row-function data)
         ]
      (process-test-file "data/test.csv" "data/oner_outputx.csv" test-row-function)
    )
    (do
        (train/read-training-file "data/train.csv")
        (if (> (count args) 1)
          (println (sort-by first (one-r/get-value-counts (keyword (second args)) (:data (:data @train/trainatom))))))
    )))
