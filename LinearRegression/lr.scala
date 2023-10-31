import org.apache.spark.sql.SparkSession
import org.apache.spark.ml.regression.LinearRegression

import org.apache.log4j._
Logger.getLogger("org").setLevel(Level.Error)

val spark = SparkSession.builder().getOrCreate()

// first column label, rest data
val data = spark.read.option("header", "true").option("inferSchema","true").format("csv").load("/Data/Clean_USA_Housing.csv")

data.printSchema