# This is the starting point of the model. Here the model is created from the initial database
# Here the data should be loaded, pre-processed, transformed and some feature engineering should be made
# Then it should call the production zone tests
import pandas as pd
from flask import request
import requests

from ModelTrainingEvaluationService.TrainingEvaluationService import TrainingEvaluationService


class ExperimentationZone:
    data = []

    def __init__(self):
        self.data_collection()
        self.data_preprocessing()
        self.data_transformation()
        self.feature_engineering()

    def experimentation_zone(self):
        self.data_collection()

    def data_collection(self):
        self.data = pd.read_csv("C:/Users/usuario/Downloads/german.data", delimiter=' ', header=None)

    def data_preprocessing(self):
        # Drops all the rows with null values
        if self.data.isnull().values.any():
            self.data = self.data.dropna()

    def data_transformation(self):
        # Replaces all categorical values by natural numbers
        self.data = pd.read_csv("C:/Users/usuario/Downloads/german.data", delimiter=' ', header=None)
        self.data[0] = self.data[0].replace(['A11', 'A12', 'A13', 'A14'], [0, 1, 2, 3])
        self.data[2] = self.data[2].replace(['A30', 'A31', 'A32', 'A33', 'A34'], [0, 1, 2, 3, 4])
        self.data[3] = self.data[3].replace(['A40', 'A41', 'A42', 'A43', 'A44', 'A45', 'A46', 'A47', 'A48',
                                            'A49', 'A410'], [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10])
        self.data[5] = self.data[5].replace(['A61', 'A62', 'A63', 'A64', 'A65'], [0, 1, 2, 3, 4])
        self.data[6] = self.data[6].replace(['A71', 'A72', 'A73', 'A74', 'A75'], [0, 1, 2, 3, 4])
        self.data[8] = self.data[8].replace(['A91', 'A92', 'A93', 'A94', 'A95'], [0, 1, 2, 3, 4])
        self.data[9] = self.data[9].replace(['A101', 'A102', 'A103'], [0, 1, 2])
        self.data[11] = self.data[11].replace(['A121', 'A122', 'A123', 'A124'], [0, 1, 2, 3])
        self.data[13] = self.data[13].replace(['A141', 'A142', 'A143'], [0, 1, 2])
        self.data[14] = self.data[14].replace(['A151', 'A152', 'A153'], [0, 1, 2])
        self.data[16] = self.data[16].replace(['A171', 'A172', 'A173', 'A174'], [0, 1, 2, 3])
        self.data[18] = self.data[18].replace(['A191', 'A192'], [0, 1])
        self.data[19] = self.data[19].replace(['A201', 'A202'], [0, 1])
        self.data[20] = self.data[20].replace([1, 2], [0, 1])

    def feature_engineering(self):
        # Make some column joins
        pass

    def train_evaluate(self):
        print('Training and evaluating...')
        training_eval = TrainingEvaluationService(self.data)

