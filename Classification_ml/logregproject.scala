// create a model if a user will click or not on an add

import org.apache.spark.ml.classification
import org.apache.spark.sql.SparkSession

import org.apache.log4j._
Logger.getLogger("org").setLevel(Level.ERROR)

val spark = SparkSession.builder().getOrCreate()

val data = spark.read.option("header", "true").option("inferSchema", "true").format("csv").load("advertising.csv")

data.printSchema

val colnames = data.columns
val firstrow = data.head(1)(0)
println("\n")
println("Example Data Row")
for(ind <- Range(1,colnames.length)){
    println(colnames(ind))
    println(firstrow(ind))
    println("\n")
}

val timedata = data.withColumn("Hour",hour(data("Timestamp")))

val logregdataall = (timedata
    .select(
        data("Clicked on Ad").as("label"), 
        $"Area Income",
        $"Age",
        $"Hour",
        $"Male",
        $"Daily Time Spent on Site",
        $"Daily Internet Usage"
    )
)

// drop missing values
val logregdata = logregdataall.na.drop()

import org.apache.spark.ml.feature.{VectorAssembler, StringIndexer, VectorIndexer, OneHotEncoder}
import org.apache.spark.ml.linalg.Vectors

// (label, features)
val assembler = (new VectorAssembler()
    .setInputCols(Array("Area Income","Hour","Age","Male","Daily Time Spent on Site", "Daily Internet Usage"))
    .setOutputCol("features")
)

val Array(training,test) = logregdata.randomSplit(Array(0.7,0.3), seed=12345)

import org.apache.spark.ml.classification.LogisticRegression
import org.apache.spark.ml.Pipeline

val lr = new LogisticRegression()

val pipeline = new Pipeline().setStages(Array(assembler, lr))

val model = pipeline.fit(training)

val results = model.transform(test)

///////////////////////////////////
// MODEL EVALUATION
///////////////////////////////////

import org.apache.spark.mllib.evaluation.MulticlassMetrics

val predictionAndLabels = results.select($"prediction", $"label").as[(Double, Double)].rdd

val metrics = new MulticlassMetrics(predictionAndLabels)

println("Confusion matrix:")
println(metrics.confusionMatrix)