What is this file?
------------------

This is essentially the project plan.  More accurately it is a list of things that seem interesting to 
study.  Other than this introduction, the document will be maintained with the oldest ideas getting pushed
down and the newest ideas at the top.

K Nearest Neighbors
===================
Want to look at a way to compare two rows of data.  Perhaps a conversion of a row of data into a 
feature vector.  This will require a comparison function which will likely need to have normalized
data.

One-r
=====
One-r is a very simple algorithm for building a model.  There is a short writeup of the its use 
at [one-r](one-r.md)

Data Analysis
=============
Building a model that will predict sales prices more accurately than choosing a dummy value will require
analyzing the train.csv data file.  The first step taken was to just look at the data file.  It is 1460
lines long so there is no need for a 'big data' solution for analysis.  With that said, the next thing
to do is to read the data into an atom.  The next step will be to look at the and construct rules which can
be used as a strategy for predicting sales price.

Initial setup
=============
created a dummy test data function that reads the test.csv file and outputs a dummy value.  This proves that
a file can be submitted and scored.
