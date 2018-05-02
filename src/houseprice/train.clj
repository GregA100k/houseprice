(ns houseprice.train
  (require [clojure.java.io :as io]
           [clojure.string :as s]
))

(def trainatom (atom {}))

  (defn comma-separate [str]
    (s/split str #","))
  (defn replace-empty-with-null [str]
    (let [split-sequence (comma-separate str)]
    (map #(if (= "" %) "NULL" %) split-sequence)))

  (defn build-column-map
    "cn is the list of column headers.  r is the row of data, and the result is a map with column headers for keys and row data for values"
    [cn r]
    (apply assoc {} (interleave cn (replace-empty-with-null r))))

  (defn csv-seq-to-map
    "take a sequence of rows from a csv file where the first row is the headings and the remaining rows are the data.  Turn each row into a map where the keys come from the first row and the values are from that row"
    [s a k]

    (let [column-name-strings (replace-empty-with-null (first s))
          column-names (map keyword column-name-strings)]
      (swap! a assoc k {:headers column-names
                        :data (doall
                               (map #(build-column-map column-names %) (rest s)))
                       })
    ))

(defn read-training-file [filename]
;; function to process each row
;; atom to store the result in
;; key to put in the atom
(with-open [rdr (io/reader filename)]
  (csv-seq-to-map (line-seq rdr) trainatom :data)
))
