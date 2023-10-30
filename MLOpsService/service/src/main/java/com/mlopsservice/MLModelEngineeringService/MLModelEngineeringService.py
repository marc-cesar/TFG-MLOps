# Read the data from the data collection ready file in Data folder
# Create the model taking into account the data
# Save the model in the model file in Data folder

import pandas as pd
import pickle
from sklearn.linear_model import LogisticRegression
from sklearn.model_selection import train_test_split

data = []
x = []
y = []
x_train = []
x_test = []
y_train = []
y_test = []
model = pd.DataFrame()

def get_data():
    global data
    data = pd.read_csv("src/main/java/com/mlopsservice/Data/PreparedData.csv", delimiter=',', header=None)

def data_splitting():
    global data, x, y ,x_train, x_test, y_train, y_test
    x = data.drop(columns=[20])
    y = data[20]
    x_train, x_test, y_train, y_test = train_test_split(x,y,test_size=0.30)

def model_training():
    global x_train, y_train, model
    model = LogisticRegression()
    model.fit(x_train, y_train)

def validate_model():
    global model, x_test, y_test
    print("Model accuracy: ", model.score(x_test, y_test))

def deploy_model():
    global model
    with open("src/main/java/com/mlopsservice/Data/model2.pkl",'wb') as f:
        pickle.dump(model, f)  

if __name__ == "__main__":
    get_data()
    data_splitting()
    model_training()
    validate_model()
    deploy_model()