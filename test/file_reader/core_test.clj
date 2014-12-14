(ns file-reader.core-test
  (:require [clojure.test :refer :all]
            [file-reader.core :refer :all]))

; (use 'file-reader.core :reload)

(defn run-main []
  (-main "-vf" "10" "/Users/westbrook/Projects/arena-online"))

(run-main)

; (if-not (zero? 0) 1 2)

; (get [1, 2, 3] 1)