# https://refactoring.guru/design-patterns/singleton/python/example

# Here flask should be configured to run the api
from flask import Flask, request, jsonify, Blueprint

from ExperimentationZone.ExperimentationZone import ExperimentationZone
from ModelScoringService.ModelScoringService import ModelScoringService, modelScoringService_endpoint
from TrainingEvaluationService.TrainingEvaluationService import TrainingEvaluationService

app = Flask(__name__)

# Register the apis
app.register_blueprint(modelScoringService_endpoint)

singleton = {
    'scoring_service': ModelScoringService(),
    'training_service': None
}


if __name__ == '__main__':
    # Call experimentation zone to start the pipeline
    ExperimentationZone()
    app.run()
