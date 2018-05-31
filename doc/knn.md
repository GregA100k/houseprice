K-Nearest Neighbors
-------------------

[K-Nearest Neighbors](https://en.wikipedia.org/wiki/K-nearest_neighbors_algorithm) is another simple classification algorithm.  For each element of the test data, it finds the K nearest rows from the training data and calculates the average Sale Price of those K training rows to be the predicted price for the test row.

The heart of the alogrithm is measuring the nearest neighbors.  The initial implementation here is very simple and works out to be a count of the number of data columns that are different between any two rows.  The choice of this measurement was made because it works for all types of data without requiring any additional analysis.

The K-Nearest Neighbors algorithm is the first in this project to be written with tests.  The default values and One-R models were coded with the intention that they would not be used again and would not benefit from test cases.  KNN seems like it can be improved upon by enhancing the distance calculation and test cases should prove to be helpful.

Results
=======
The file generated using K=5 was submitted to the Kaggle competition and again improved the standings.  The improvement was a bigger jump than the One-R improvement, 1402 positions to 3420.  Even though this was a big improvement, we are still not into the top half of the rankings.
