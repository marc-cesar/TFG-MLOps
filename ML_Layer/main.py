# Here it should be configured flask to run the api
from ExperimentationZone.ExperimentationZone import ExperimentationZone


if __name__ == '__main__':
    exp = ExperimentationZone().get_data()
    print('okey!')