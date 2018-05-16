# houseprice

A Clojure project designed to help members of the clojure.mn meetup group learn about machine learning

The first activity for the group will be the Kaggle.com competition 
[House Prices: Advanced Regression Techniques](https://www.kaggle.com/c/house-prices-advanced-regression-techniques)

If you want to join the clojure.mn team for the competition, you can either help with the coding, or 
if you want to be able submit files to the competition, you will need to create a Kaggle account,
join the competition, and then join the clojure.mn team.

The first implementation reads the test.csv file from the competition and creates a submission file
matching each ID with a default value.


The programs so far expect to find the files provided for the contest in the data folder.  Note, it does
not seem necessary to add the data files to the repo.


The second submission used the [one-r](doc/one-r.md) technique, and a little manual intervention, to pick a single column
to predict the sale price.  The column chosed was OverallQual.  A writeup of the process will be added 
soon in a separate commit.
