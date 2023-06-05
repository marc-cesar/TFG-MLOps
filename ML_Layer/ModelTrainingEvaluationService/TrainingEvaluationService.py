# In this model the data arrives already treated, and here is where the data splits, trains into a model and validates
# its efficacy. A configuration file should get the parameters.

import pandas as pd
from sklearn.discriminant_analysis import QuadraticDiscriminantAnalysis
from sklearn.metrics import accuracy_score
from sklearn.model_selection import train_test_split


class TrainingEvaluationService:
    data = pd.DataFrame()
    x = []
    y = []
    model = pd.DataFrame()
    x_train = []
    x_test = []
    y_train = []
    y_test = []
    accuracy_score = 0

    def __init__(self, data):
        self.data = data
        self.split_data()
        self.model_training()
        self.model_validation()

    def get_model(self):
        return self.model

    def model_validation(self):
        predictions = self.model.predict(self.x_test)
        self.accuracy_score = accuracy_score(self.y_test, predictions)
        print("Accuracy: " + str(accuracy_score(self.y_test, predictions)))

    def model_training(self):
        # classifiers = ["AdaBoost", "Decision Tree", "GaussianNB", "QuadraticDiscriminantAnalysis()"]
        self.model = QuadraticDiscriminantAnalysis()
        self.model.fit(self.x_train, self.y_train)

    def split_data(self):
        self.x = self.data.drop(columns=[20])
        self.y = self.data[20]
        self.x_train, self.x_test, self.y_train, self.y_test = train_test_split(self.x, self.y, test_size=0.30)
