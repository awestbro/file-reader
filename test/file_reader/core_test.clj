(ns file-reader.core-test
  (:require [clojure.test :refer :all]
            [file-reader.core :refer :all]))

; (use 'file-reader.core :reload)

(defn run-main []
  (-main "-f" "10000" "/Users/westbrook/Projects/file-reader/"))

(run-main)