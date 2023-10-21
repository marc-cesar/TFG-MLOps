import mysql.connector
import pandas as pd
# read the data from csv file
# read the data from the database (requests)
# if a request has feedback, take the feedback
# Treat the data (preprocessing, transformation, feature engineering)
# Save the data in the database (maybe return to the java file)

data = []

def data_collection():
    # Reads the data from the .data file and the database
    global data 
    data = pd.read_csv("MLOpsService/service/src/main/java/com/mlopsservice/Data/german.data", index_col=False, delimiter=' ', header=None)

def read_data_from_database():
    global data
    config = {
        "user": "root",
        "password": "root1234",
        "host": "localhost",
        "port": "3306",
    }

    connection = mysql.connector.connect(**config)
    cursor = connection.cursor()
    cursor.execute("select * from mlops.requests")

    rows_list = []
    for row in cursor.fetchall():
        print(row)
        (id, field0, field1, field10, field11, field12, field13, field14, field15, field16,
        field17, field18, field19, field2, field3, field4, field5, field6, field7, 
        field8, field9, prediction, feedback) = row
        # Check if there is feedback. If there is, take the feedback
        # Else, take the prediction given by the model
        if(feedback != None):
            solution = feedback
        else:
            solution = prediction

        next_index = len(data)

        data.loc[next_index] = [field0, field1, field2, field3, field4, field5, field6, field7,
        field8, field9, field10, field11, field12, field13, field14, field15,
        field16, field17, field18, field19, solution]

    cursor.close()
    connection.close()

def data_preprocessing():
    # At the moment we will only delete the null values
    global data
    if data.isnull().values.any():
        data = data.dropna()

def data_transformation():
        # Specific function for the expected data
    global data
    # Replaces all categorical values by natural numbers
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

def saveFile():
    global data
    data = data.to_csv('MLOpsService/service/src/main/java/com/mlopsservice/Data/PreparedData.csv', index=False, header=False)


if __name__ == "__main__":
    data_collection()
    data_preprocessing()
    data_transformation()
    read_data_from_database() 
    feature_engineering()
    saveFile()