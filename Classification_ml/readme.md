The classification algorithm use its called Logistic Regression.

Can read more in Sections 4-4.3 of Introduction of Statistical Learning by Gareth James.


Some examples of classification problems:
- Spam versus "Ham" emails
- Loan Default(yes/no)
- Cancer diagnosis

Logistic regression is not predicting a continuos value until now.
For this algorithm is like that the X axis is the threshold where users will be able to pay a loan or not.
We can transform a linear regression line to a logistic regression curve

The sigmoid(aa Logistic) Function takes in any value and utputs it to be between 0 and 1

Formula = Beta(z) = 1 / (1 + e^-z)
No matter the value you input of z its always going to return a value between 0 and 1.


## Confusion Matrix

[Confusion Matrix](conf_matrix.png)