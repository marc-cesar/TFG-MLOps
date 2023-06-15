# Here there should be the API Route to predict the client the user has entered
# And an API Post to replace the ML model
# The model is stored here
import pandas as pd
from flask import request, Blueprint
import joblib

modelScoringService_endpoint = Blueprint('modelScoringService_endpoint', __name__, template_folder='templates')


@modelScoringService_endpoint.route('/Predict', methods=['GET'])
def predict():
    with open("Data/MachineLearningModel.pkl", 'rb') as file:
        model = joblib.load(file)
    return str(model.predict())


