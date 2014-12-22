(ns file-reader.core
	(:require [clojure.tools.cli :refer [parse-opts]]
              [clojure.string :as string])
	(:gen-class))

(def num-processed-bytes (atom 0))
(def num-processed-files  (atom 0))

(defn if-not-zero [x]
  "Return true if a number is not zero, Used for filtering out default param options"
  (not (zero? x)))

(defn get-files-by-size [files max-bytes]
  (loop [file (first files)
         file-list files
         size 0
         num-files 0
         output []]
    (if (or (empty? file-list) (> (+ (.length file) size) max-bytes)) {:files output :size size :num-files num-files}
     	(recur (first file-list) (rest file-list) (+ size (.length file)) (inc num-files) (conj output file))
    )))

(defn get-files-by-number [files max-number]
  (loop [file (first files)
         file-list files
         size 0
         num-files 0
         output []]
    (if (or (empty? file-list) (> (inc num-files) max-number)) {:files output :size size :num-files num-files}
     	(recur (first file-list) (rest file-list) (+ size (.length file)) (inc num-files) (conj output file))
    )))

(defn get-files [dir conf]
  	"Gets a list of files base on the directory"
 	(let [{:keys [verbose num-bytes num-files]} conf
         byte-mode (if-not-zero num-bytes)
         size-mode (if-not-zero num-files)
         file-list (file-seq (clojure.java.io/file dir))]

    (if byte-mode
      (get-files-by-size file-list num-bytes)
      (get-files-by-number file-list num-files))))

(defn file-processor [file]
  (println "Some intensitve function over file: " + (.getName file)))


;; Command Line Option Configuration

(def cli-options
	[["-f" "--f FILE" "Number of files to process"
	:id :num-files
	:default 0
	:parse-fn #(Integer/parseInt %)]
	["-s" "--s SIZE" "Read until byte size is met"
  :id :num-bytes
	:default 0
	:parse-fn #(Long/parseLong %)]
 ;    ["-v" nil "Prints status of file-reader as information is being processed"
 ;    :id :verbose
 ;    :default 0
 ;    :assoc-fn (fn [m k _] (update-in m [k] inc))]
	["-h" "--help"]])

(defn usage [options-summary]
	(->> ["File Reader"
		""
		"Usage: file-reader [options] directory"
		""
		"This program reads a directory of files until a size or file number is met"
  		""
    	"-f FILES, Number of files to read"
     	"-s SIZE, Number of bytes to read"]
		(string/join "\n")))

(defn error-msg [errors]
	(str "The following errors occurred while parsing your command:\n\n"
		 (string/join "\n" errors)))

(defn exit [status msg]
	(println msg)
	(System/exit status))

(defn -main
	"Read files until number limit or size limit is reached"
	[& args]
	(let [{:keys [options arguments errors summary]} (parse-opts args cli-options)]
		; comment out the following cond when testing
		(cond
			(:help options) (exit 0 (usage summary))
			(not= (count arguments) 1) (exit 1 (usage summary))
			errors (exit 1 (error-msg errors)))
 		(let [start     (System/currentTimeMillis)
         	  file-list (get-files (last args) options)]
     		(pmap file-processor (:data file-list))
       		(println "Processed: " (:num-files file-list) " files, with a total size of " (:size file-list) " bytes in " (- (System/currentTimeMillis) start) "ms"))))









