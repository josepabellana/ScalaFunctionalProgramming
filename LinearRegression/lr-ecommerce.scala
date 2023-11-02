import org.apache.spark.ml.regression.LinearRegression

import org.apache.log4j._
Logger.getLogger("org").setLevel(Level.ERROR)

import org.apache.spark.sql.SparkSession

val spark = SparkSession.builder().getOrCreate()

val data = spark.read.option("header", "true").option("inferSchema", "true").format("csv").load("Clean_Ecommerce.csv")

data.printSchema