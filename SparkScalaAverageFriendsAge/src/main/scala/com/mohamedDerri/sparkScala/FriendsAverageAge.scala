package com.mohamedDerri.sparkScala

import org.apache.spark._
import org.apache.spark.SparkContext
import org.apache.log4j._

object FriendsAverageAge {

  def parseLine(line : String) : (String, Int) = {
    val fields = line.split(",")

    val name = fields(1).toString
    val age = fields(2).toInt

    (name, age)
  }

  def main(args : Array[String]) : Unit = {

    LogManager.getLogger("org").setLevel(Level.ERROR)

    val sc = new SparkContext("local[*]", "FriendsAverageAge")

    val lines = sc.textFile("fakeFriends.csv")

    val rdd = lines.map(parseLine)

    val rddCount = rdd.mapValues(x => (x, 1))

    val totalByName = rddCount.reduceByKey((x, y) => (x._1 + y._1, x._2 + y._2))

    val avarageAgeByName = totalByName.mapValues(x => x._1 / x._2)

    val results = avarageAgeByName.collect()

    results.sorted.foreach(println)
  }
}
