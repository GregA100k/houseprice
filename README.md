# houseprice

A Clojure project designed to help members of the clojure.mn meetup group learn about machine learning

The first activity for the group will be the Kaggle.com competition
[House Prices: Advanced Regression Techniques](https://www.kaggle.com/c/house-prices-advanced-regression-techniques)

If you want to join the clojure.mn team for the competition, you can either help with the coding, or
if you want to be able submit files to the competition, you will need to create a Kaggle account,
join the competition, and then join the clojure.mn team.

Data
====
The programs, so far, expect to find the files provided for the contest in the data folder.  Note, it does
not seem necessary to add the data files to the repo so you will need to create a
data folder under the houseprice project folder and place the downloaded train.csv
and test.csv files from Kaggle [competition data page](https://www.kaggle.com/c/house-prices-advanced-regression-techniques/data).

Submissions
===========
The first implementation reads the test.csv file from the competition and creates a submission file
matching each ID with a default value.

The second submission used the [one-r](doc/one-r.md) technique, and a little manual intervention, to pick a single column
to predict the sale price.  The column chosen was OverallQual.  

The third submission was created using [K Nearest Neighbors](doc/knn.md)  The distance function used is the sum of the number of columns that do not match.  This was easy to implement and works for all types of data.
