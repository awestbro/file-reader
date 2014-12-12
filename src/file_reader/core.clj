(ns file-reader.core
  (:gen-class))

(def num-processed-bytes (atom 0))
(def num-processed-files  (atom 0))

(defn get-files [dir conf]
  "Gets a list of files base on the directory")

(defn parse-conf [args]
  (println "args: " args)
  (println "args[1]: " (get args 1))
  (let [file-path (first args)
        parameter (get args 1)
        value 	  (get args 2)]
    (println "Recieved " file-path ", " parameter ", " value)))

(defn -main
  "Read files until number limit or size limit is reached"
  [& args]
  (let [conf (parse-conf args)]
    (println conf)))