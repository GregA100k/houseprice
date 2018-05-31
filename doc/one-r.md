One-r
-----
One-R is short for one rule.  It is a simple way to get a view into which columns have the most direct impact
on the classification value.

[Classification - Basic Methods](http://chem-eng.utoronto.ca/~datamining/Presentations/Basic_Methods.pdf)
shows how to calculate the one rule that works best for a simple weather example.  For each attribute, they
made tables of each value and how many yes or no values were produced for that value, then chose the larger
number as the rule.  

Of course, when you want to use something it is never as simple as the simple examples.  Since this is a regression
problem instead of a classification problem we can't just take the most popular value.  Instead, I chose to take the
average SalePrice for each value of a column attribute.  To use the home sale data, for an attribute like HouseStyle,
the created rule says that for a value like 1Story, use the average Sale Price for all 1Story houses and similar
for 2Story and all the other values.

After a rule was built for each column attribute, it was tested against all the training data to see which rule
provided the most predictions that fell within $5000 of the actual Sale Price

Below is a list of all columns and the accuracy of the rule generated for that column.  The twelve most accurate
columns are all size measurements.  These columns have a lot of values which means fewer rows for each value.  That
also means that testing against the training data will be overfit and produce results that will not be duplicated in
the test data.

The first non-measurement column was OverallQual.  Intuitively, this sounds like a value that should help predict
the price of a house.  This fact was proved out by running the test data against the  one-r rule for
OverallQual.

Results
=======
The results of this model produced a ranking 357 places higher than the default price model.  Now ranked 4745.



[:BsmtExposure 35/696]
[:HalfBath 35/696]
[:MasVnrType 71/1391]
[:BsmtFullBath 71/1391]
[:LotShape 75/1387]
[:Electrical 40/691]
[:PavedDrive 83/1379]
[:RoofStyle 42/689]
[:CentralAir 5/81]
[:YrSold 44/687]
[:Street 45/686]
[:BldgType 45/686]
[:MiscFeature 45/686]
[:MoSold 45/686]
[:Alley 91/1371]
[:GarageQual 91/1371]
[:Condition2 46/685]
[:OverallCond 47/684]
[:ExterCond 95/1367]
[:BsmtHalfBath 95/1367]
[:KitchenAbvGr 95/1367]
[:Functional 95/1367]
[:Heating 48/683]
[:GarageCond 48/683]
[:LandSlope 97/1365]
[:PoolQC 97/1365]
[:Utilities 49/682]
[:BsmtCond 49/682]
[:BsmtFinType2 49/682]
[:BedroomAbvGr 49/682]
[:LandContour 50/681]
[:BsmtFinType1 50/681]
[:HouseStyle 3/40]
[:RoofMatl 3/40]
[:SaleType 3/40]
[:Condition1 103/1359]
[:MiscVal 103/1359]
[:SaleCondition 103/1359]
[:PoolArea 52/679]
[:Exterior2nd 107/1355]
[:MSZoning 54/677]
[:Exterior1st 54/677]
[:Fence 54/677]
[:MSSubClass 55/676]
[:LotConfig 55/676]
[:3SsnPorch 55/676]
[:HeatingQC 56/675]
[:LowQualFinSF 56/675]
[:FireplaceQu 59/672]
[:TotRmsAbvGrd 7/79]
[:GarageType 121/1341]
[:Foundation 61/670]
[:Fireplaces 62/669]
[:YearRemodAdd 66/665]
[:GarageFinish 135/1327]
[:FullBath 137/1325]
[:ExterQual 147/1315]
[:LotFrontage 75/656]
[:GarageYrBlt 75/656]
[:BsmtQual 151/1311]
[:KitchenQual 9/77]
[:ScreenPorch 155/1307]
[:EnclosedPorch 80/651]
[:GarageCars 81/650]
[:Neighborhood 169/1293]
[:YearBuilt 5/38]
[:OverallQual 183/1279]
[:BsmtFinSF2 103/628]
[:OpenPorchSF 113/618]
[:WoodDeckSF 245/1217]
[:MasVnrArea 146/585]
[:GarageArea 395/1067]
[:2ndFlrSF 397/1065]
[:BsmtFinSF1 250/481]
[:1stFlrSF 549/913]
[:BsmtUnfSF 33/53]
[:TotalBsmtSF 295/436]
[:GrLivArea 21/22]
[:LotArea 1001/461]
