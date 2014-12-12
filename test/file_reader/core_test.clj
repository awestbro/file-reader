(ns file-reader.core-test
  (:require [clojure.test :refer :all]
            [file-reader.core :refer :all]))

; (use 'file-reader.core :reload)

(defn run-main []
  (-main "-f" "20" "/Users/westbrook/Projects/arena-online"))

(run-main)

; (get [1, 2, 3] 1)