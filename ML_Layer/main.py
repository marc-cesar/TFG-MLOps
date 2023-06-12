# Here flask should be configured to run the api
from flask import Flask, request, jsonify, Blueprint

from ExperimentationZone.ExperimentationZone import ExperimentationZone
from ModelScoringService.ModelScoringService import modelScoringService_endpoint

app = Flask(__name__)

# Register the apis
app.register_blueprint(modelScoringService_endpoint)

if __name__ == '__main__':
    app.run()
    exp = ExperimentationZone()
    exp.deploy_model()
