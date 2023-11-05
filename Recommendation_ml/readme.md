The two mos common types of recommender systems are **Content-Based** and **Collaborative Filtering(CF)**
- CF produces recommendations based on the knowledge of users' attitude to items, that is it uses the "wisdom of the crowd" to recommend items.
- Content-based focuses on the attributes of the items and give you recommendations based on the similarity between them

CF is more commonly used as it gives better results and is easier to understand
The algorithm has the ability to do feature learning on its own which means that it can start to learn for itself what features to use

These techniques also aim to fill missing entries of a user-item association matrix
spark.ml currently supports model-based collaborative filtering, in which users and products are described by a small set of latent factoris that can be used to predict missing entried.
spark.ml uses the alternating least squares(ALS) algorithm to learn these latent factors

ALS is basically a Matrix Factorization approach to implement a recommendation algorithm, you decompose your user/item matrix into lower dimensional user factors and item factors.