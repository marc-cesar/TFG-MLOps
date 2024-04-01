# Read the data from the data collection ready file in Data folder
# Create the model taking into account the data
# Save the model in the model file in Data folder

import io
import sys
from joblib import dump
import joblib
import pandas as pd
import pickle
from sklearn.linear_model import LogisticRegression
from sklearn.model_selection import train_test_split
import requests
from sklearn.tree import DecisionTreeClassifier

data = []
x = []
y = []
x_train = []
x_test = []
y_train = []
y_test = []
model = pd.DataFrame()

def get_data(data_file):
    global data
    data = pd.read_csv(data_file, delimiter=',', header=None)

def data_splitting():
    global data, x, y ,x_train, x_test, y_train, y_test
    x = data.drop(columns=[20])
    y = data[20]
    x_train, x_test, y_train, y_test = train_test_split(x,y,test_size=0.30)

def model_training():
    global x_train, y_train, model
    model = DecisionTreeClassifier()
    model.fit(x_train, y_train)

def validate_model():
    global model, x_test, y_test
    print("Model accuracy: ", model.score(x_test, y_test))

def deploy_model():
    # Send a post request so that the model scoring service loads the model
    global model
    # with open('src/main/java/com/backend/mlopsbackend/Data/model.joblib','wb') as model_file:
    #     joblib.dump(model,model_file)

    buffer = io.BytesIO()
    dump(model, buffer)
    buffer.seek(0)
    files = {'model': buffer}
    response = requests.post("http://127.0.0.1:5000/LoadModel",files=files)
    # It's good practice to check the response
    if response.status_code == 200:
        print("Model deployed successfully.")
    else:
        print("Failed to deploy model. Status code:", response.status_code)

if __name__ == "__main__":
    data_path = sys.argv[2]
    get_data(data_path)
    data_splitting()
    model_training()
    validate_model()
    deploy_model()