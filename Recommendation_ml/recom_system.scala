import org.apache.spark.ml.recommendation.ALS 

val ratings = spark.read.option("header","true").option("inferSchema","true").csv("movie_rating.csv")


ratings.head()

ratings.printSchema

// val data = ratings.na.drop()

val Array(training,test) = ratings.randomSplit(Array(0.8,0.2), seed=12345)

val als = new ALS().setMaxIter(5).setRegParam(0.01).setUserCol("UserId").setItemCol("MovieId").setRatingCol("Rating")

val model = als.fit(training)

val predictions = model.transform(test)

// predictions.show()

// How good our model did?
import org.apache.spark.sql.functions._
val error = predictions.select(abs($"Rating"-$"prediction"))
error.na.drop().describe().show()