// example from the spark tunning and model selection documentation

import org.apache.spark.ml.evaluation.RegressionEvaluator
import org.apache.spark.ml.regression.LinearRegression
import org.apache.spark.ml.tuning.{ParamGridBuilder, TrainValidationSplit}

val data = spark.read.format("libsvm").load("sample_linear_regression_data.txt")

// Train test split
val Array(training, test) = data.randomSplit(Array(0.9,0.1), seed=12345)

data.printSchema

val lr = new LinearRegression()

// I am creating different models where I can test with diff parameters.
val paramGrid = new ParamGridBuilder().addGrid(lr.regParam, Array(0.1, 0.01)).addGrid(lr.fitIntercept).addGrid(lr.elasticNetParam, Array(0.0, 0.5, 1.0)).build()

val trainValidationSplit = new TrainValidationSplit().setEstimator(lr).setEvaluator(new RegressionEvaluator()).setEstimatorParamMaps(paramGrid).setTrainRatio(0.8)

val model = trainValidationSplit.fit(training)

model.transform(test).select("features", "label", "prediction").show()

println(s"Best Model is ${model.bestModel}")
println(s"Param Map used = ${model.getEstimatorParamMaps}")