(ns file-reader.core
	(:require [clojure.tools.cli :refer [parse-opts]]
              [clojure.string :as string])
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

;; Command Line Option Configuration

(def cli-options 
	[["-f" "--f FILE" "Number of files to process"
	:id :num-files
	:default -1
	:parse-fn #(Integer/parseInt %)]
	 ["--s" "--s SIZE" "Read until byte size is met"
  :id :num-bytes
	:default -1
	:parse-fn #(Long/parseLong %)]
	 ["-h" "--help"]])

(defn usage [options-summary]
	(->> ["File Reader"
		""
		"Usage: file-reader [options] directory"
		""
		"This program reads a directory of files until a size or file number is met"]
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
	; (cond
	; 	(:help options) (exit 0 (usage summary))
	; 	(not= (count arguments) 1) (exit 1 (usage summary))
	; 	errors (exit 1 (error-msg errors)))
	(println options)))