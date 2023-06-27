@echo off
hadoop fs -mkdir inputFolder
hadoop fs -put /home/cloudera/Desktop/groupData.txt inputFolder

hadoop fs -rm -r outputFolder
hadoop jar '/home/cloudera/Desktop/StripeMapReduce.jar' inputFolder outputFolder

hadoop fs -cat outputFolder/part-r-00000
