# Flask
import os
import joblib
import time
import datetime
import pandas as pd
from flask import Flask,request

app = Flask(__name__)

MODEL_VERSION_FILE = 'model_version.txt'
last_checked = 0
model_version = None
model = pd.DataFrame()

def check_for_model_update():
    global model, last_checked, model_version
    current_time = time.time()
    # Check for an update every 60 seconds (or any suitable interval)
    if current_time - last_checked > 60:
        last_checked = current_time
        with open(MODEL_VERSION_FILE, 'r') as file:
            current_version = file.read().strip()
            if current_version != model_version:
                model_version = current_version
                with open('/shared_data/model.pkl','rb') as model_file:
                    model = joblib.load(model_file)
                print("Model reloaded")

def update_model_version():
    global model_version
    timestamp = datetime.datetime.now().strftime("%Y%m%d%H%M%S")
    with open(MODEL_VERSION_FILE, 'w') as file:
        file.write(timestamp)



@app.route("/NewModel", methods=['GET'])
def reload_model():
    # Write the file 
    update_model_version()
    print("Model Reloaded")
    return "Model Reloaded"

@app.route("/Predict", methods=['POST'])
def predict():
    check_for_model_update()
    global model
    data = request.get_json()
    d = {str(key): [value[0]] for key, value in data.items()}
    df = pd.DataFrame(data=d)
    predict = model.predict(df)[0]
    
    return str(predict) # (1 = Good,  2 = Bad) => (0 = Good, 1 = Bad)