# Here there should be the API Route to predict the client the user has entered
# And an API Post to replace the ML model
# The model is stored here
import pandas as pd
from flask import request, Blueprint
import joblib

modelScoringService_endpoint = Blueprint('modelScoringService_endpoint', __name__, template_folder='templates')

model = pd.DataFrame()


@modelScoringService_endpoint.route('/NewModel', methods=['POST'])
def new_model():
    content = request.json
    model = content['model']
    joblib.dump(model, 'model.pkl')


@modelScoringService_endpoint.route('/Predict', methods=['GET'])
def predict():
    model = joblib.load('model.pkl')
    # GetRow
    r = request.json
    return "Perfect!"
