import org.apache.spark.ml.evaluation.RegressionEvaluator
import org.apache.spark.ml.regression.LinearRegression
import org.apache.spark.ml.tuning.{ParamGridBuilder, TrainValidationSplit}

import org.apache.log4j._
Logger.getLogger("org").setLevel(Level.ERROR)

// Start a simple Start Session
import org.apache.spark.sql.SparkSession
val spark = SparkSession.builder().getOrCreate()

val data = spark.read.option("header","true").option("inferSchema","true").format("csv").load("../LinearRegression/Clean_USA_Housing.csv")

data.printSchema

// Trying to predic the price!
import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.ml.linalg.Vectors

val df = data.select(data("Price").as("label"),$"Avg Area Income",$"Avg Area House Age",$"Avg Area Number of Rooms",$"Avg Area Number of Bedrooms",$"Area Population")

val assembler = new VectorAssembler().setInputCols(Array("Avg Area Income","Avg Area House Age","Avg Area Number of Rooms","Avg Area Number of Bedrooms","Area Population")).setOutputCol("features")

val output = assembler.transform(df).select($"label", $"features")

// training and test data
val Array(training,test) = output.select("label", "features").randomSplit(Array(0.7,0.3), seed=12345)

// model
val lr = new LinearRegression()

// parameter Grid Builder
val paramGrid = new ParamGridBuilder().addGrid(lr.regParam, Array(10000000,0.001)).build()

// train split(holdout)
val trainvalsplit  = (new TrainValidationSplit()
    .setEstimator(lr)
    .setEvaluator(new RegressionEvaluator().setMetricName("r2")) // standard is RMSE, we can set it to r2
    .setEstimatorParamMaps(paramGrid)
    .setTrainRatio(0.8))

val model = trainvalsplit.fit(training)

model.transform(test).select("features", "label", "prediction").show()

// model.validationMetrics To check how good the model is, we can see the first one is shit, the other has 92%

/*
 You can keep adding a new model with different parameters and also set What metric you want to use to test them
*/