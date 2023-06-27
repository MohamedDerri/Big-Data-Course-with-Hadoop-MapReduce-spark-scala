@echo off
hadoop fs -mkdir inputFolder
hadoop fs -put /home/cloudera/Desktop/access_log inputFolder

hadoop fs -rm -r outputFolder
hadoop jar '/home/cloudera/Desktop/MapReduceAverage.jar' inputFolder outputFolder

hadoop fs -cat outputFolder/part-r-00000
