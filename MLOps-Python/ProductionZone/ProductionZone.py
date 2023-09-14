from TrainingEvaluationService.TrainingEvaluationService import TrainingEvaluationService
from flask import Blueprint, request

productionZoneEndpoint = Blueprint('productionZone_endpoint', __name__, template_folder='templates')


@productionZoneEndpoint.route('/Retrain', methods=['POST'])
def retrain():
    # Get the new data from the request and call the training service
    from main import singleton
    training_service = singleton['training_service']
    training_service.add_new_data(request.get_json())
