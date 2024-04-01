# Flask
import os
import joblib
import time
import datetime
import pandas as pd
from flask import Flask,request, jsonify
from io import BytesIO


MODEL_VERSION_FILE = 'model_version.txt'
last_checked = 0
model_version = None
model = None

app = Flask(__name__)

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
    #print("test")
    #check_for_model_update()
    global model
    print(model)
    data = request.get_json()
    d = {str(key): [value[0]] for key, value in data.items()}
    df = pd.DataFrame(data=d)
    print(df)
    predict = model.predict(df)[0]
    
    return str(predict) # (1 = Good,  2 = Bad) => (0 = Good, 1 = Bad)

@app.route("/LoadModel", methods=['POST'])
def loadModel():
    global model
    if 'model' not in request.files:
        return jsonify({"error": "No model file"}), 400
    model_file = request.files['model']
    # Deserialize model
    model_stream = BytesIO(model_file.read())
    try:
        model = joblib.load(model_stream)
        return jsonify({"message": "Model received and loaded successfully"}), 200
    except Exception as e:
        return jsonify({"error": str(e)}), 500
    
if __name__ == '__main__':
    app.run(debug=True)