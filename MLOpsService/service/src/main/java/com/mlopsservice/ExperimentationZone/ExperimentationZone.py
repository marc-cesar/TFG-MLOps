import pandas as pd
import pickle
from sklearn.model_selection import train_test_split
from sklearn.tree import DecisionTreeClassifier
from sklearn.metrics import accuracy_score

data = []
x = []
y = []
x_train = []
x_test = []
y_train = []
y_test = []
model = pd.DataFrame()

def Experimentate():
    data_collection()
    data_preprocessing()
    data_transformation()
    feature_engineering()
    data_splitting()
    model_training()
    validate_model()
    deploy_model()

def data_collection():
    # Reads the data from the .data file
    global data 
    data = pd.read_csv("MLOpsService/service/src/main/java/com/mlopsservice/Data/german.data", delimiter=' ', header=None)

def data_preprocessing(): 
    # At the moment we will only delete the null values
    global data
    if data.isnull().values.any():
        data = data.dropna()

def data_transformation():
    # Specific function for the expected data
    global data
    # Replaces all categorical values by natural numbers
    data = pd.read_csv("MLOpsService/service/src/main/java/com/mlopsservice/Data/german.data", delimiter=' ', header=None)
    data[0] = data[0].replace(['A11', 'A12', 'A13', 'A14'], [0, 1, 2, 3])
    data[2] = data[2].replace(['A30', 'A31', 'A32', 'A33', 'A34'], [0, 1, 2, 3, 4])
    data[3] = data[3].replace(['A40', 'A41', 'A42', 'A43', 'A44', 'A45', 'A46', 'A47', 'A48',
                             'A49', 'A410'], [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10])
    data[5] = data[5].replace(['A61', 'A62', 'A63', 'A64', 'A65'], [0, 1, 2, 3, 4])
    data[6] = data[6].replace(['A71', 'A72', 'A73', 'A74', 'A75'], [0, 1, 2, 3, 4])
    data[8] = data[8].replace(['A91', 'A92', 'A93', 'A94', 'A95'], [0, 1, 2, 3, 4])
    data[9] = data[9].replace(['A101', 'A102', 'A103'], [0, 1, 2])
    data[11] = data[11].replace(['A121', 'A122', 'A123', 'A124'], [0, 1, 2, 3])
    data[13] = data[13].replace(['A141', 'A142', 'A143'], [0, 1, 2])
    data[14] = data[14].replace(['A151', 'A152', 'A153'], [0, 1, 2])
    data[16] = data[16].replace(['A171', 'A172', 'A173', 'A174'], [0, 1, 2, 3])
    data[18] = data[18].replace(['A191', 'A192'], [0, 1])
    data[19] = data[19].replace(['A201', 'A202'], [0, 1])
    data[20] = data[20].replace([1, 2], [0, 1])

def feature_engineering():
    # Make some column joins
    pass

def data_splitting():
    global x, y, x_train, x_test, y_train, y_test, model, data
    x = data.drop(columns=[20])
    y = data[20]
    x_train, x_test, y_train, y_test = train_test_split(x,y,test_size=0.30)

def model_training():
    global model, x_train,y_train
    # classifiers = ["AdaBoost", "Decision Tree", "GaussianNB", "QuadraticDiscriminantAnalysis()"]
    model = DecisionTreeClassifier()
    model.fit(x_train,y_train)   

def validate_model():
    global model, x_test, y_test
    predictions = model.predict(x_test)
    acc_score = accuracy_score(y_test,predictions)
    print("Accuracy: " + str(accuracy_score(y_test, predictions)))

def deploy_model():
    # deploy model into production
    global model
    with open('MLOpsService/service/src/main/java/com/mlopsservice/Data/model.pkl','wb') as model_file:
        pickle.dump(model,model_file)

if __name__ == '__main__':
    Experimentate()
