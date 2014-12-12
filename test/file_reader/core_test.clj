(ns file-reader.core-test
  (:require [clojure.test :refer :all]
            [file-reader.core :refer :all]))

; (use 'file-reader.core :reload)

(defn run-main []
  (-main "/Users/westbrook/Projects/arena-online" "files" 10))

(run-main)

(get [1, 2, 3] 1)