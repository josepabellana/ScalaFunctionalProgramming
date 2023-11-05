### Topics
- Train Test Splits
    You want to measure how your test data works. Remember to not re-use the same test data in your model, so do not evaluate your model's performance with re-tested data. Data that is checked:
    Regression
    - R^2
    - RMSE
    Classification (Confusion matrix)
    - Precission
    - Recall
    Clustering
    - Within Sum of Squares Error
- Holdout Sets
    Separate from the trainig and test sets, In this process you use the training data to fit your model, you use the test set to evaluate and adjust your model. You can use the test set over and over again. Finally, before deploying your model, you check it against the holdout to get some final metrics on performance.
- Parameter Grids
    You can add optional parameters to Machine Learning Algorithms. You can create multiple models, train them accross the grid, and Spark reports back which model performed best.
- Scala and Spark for Model Evaluation
    Spark makes all the previous processes easy to implement with the following 3 object types:
    - Evaluators
    - ParamGridBuilders
    - TrainValidationSplit
- Bias Variance Trade-Off
    From data Engineering Theory. This is the point where we are adding just noise by adding model complexity(flexibility)
    The training error goes down as it hat to, but the test error is starting to go up
    The model after the bias trade-ogg begins to overfit.
    [Bias Variance TradeOff](biasVarTradeOff.png)
    Imagine that the center of the target is a model that perfeclty predicts the correct values
    As we move away from the bulls-eye, our predictions get worse and worse.
    [Bias Variance TradeOff2](biasVarTradeOff2.png)

