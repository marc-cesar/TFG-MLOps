# Here there should be the API Route to predict the client the user has entered
# And an API Post to replace the ML model
# The model is stored here
import pandas as pd
from flask import request, Blueprint
import joblib

modelScoringService_endpoint = Blueprint('modelScoringService_endpoint', __name__, template_folder='templates')


@modelScoringService_endpoint.route('/Predict', methods=['POST'])
def predict():
    with open("Data/MachineLearningModel.pkl", 'rb') as file:
        model = joblib.load(file)
    # Get Data with JSON
    data = request.get_json()

    d = {'0': [data['0'][0]],
         '1': [data['1'][0]],
         '2': [data['2'][0]],
         '3': [data['3'][0]],
         '4': [data['4'][0]],
         '5': [data['5'][0]],
         '6': [data['6'][0]],
         '7': [data['7'][0]],
         '8': [data['8'][0]],
         '9': [data['9'][0]],
         '10': [data['10'][0]],
         '11': [data['11'][0]],
         '12': [data['12'][0]],
         '13': [data['13'][0]],
         '14': [data['14'][0]],
         '15': [data['15'][0]],
         '16': [data['16'][0]],
         '17': [data['17'][0]],
         '18': [data['18'][0]],
         '19': [data['19'][0]],
         }
    df = pd.DataFrame(data=d)
    print(model.predict(df))
    return str(model.predict(df)[0])
