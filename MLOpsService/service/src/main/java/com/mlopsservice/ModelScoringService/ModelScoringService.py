# Flask
import os
import pickle
import pandas as pd
from flask import Flask,request

app = Flask(__name__)

model = pd.DataFrame()

def load_model():
    global model
    with open('src/main/java/com/mlopsservice/Data/model.pkl','rb') as model_file:
        model = pickle.load(model_file)

@app.route("/Predict", methods=['POST'])
def predict():
    global model
    data = request.get_json()
    d = {
            '0': [data['0'][0]],
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
    predict = model.predict(df)[0]
    
    return str(predict)

@app.route("/NewModel", methods=['PUT'])
def reload_model():
    load_model()

if __name__ == '__main__':
    load_model()
    app.run()