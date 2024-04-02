# Use the official Python image.
# Adjust the tag as needed to match the version of Python you're using.
FROM python:3.8-slim

# Set the working directory in the container.
WORKDIR /app

# Copy the Python requirements file into the container and install dependencies.
COPY requirements.txt .
RUN pip install --no-cache-dir -r requirements.txt

# Copy the rest of your Flask application's source code into the container.
COPY . /app

EXPOSE 5000

# configure the container to run in an executed manner
ENTRYPOINT [ "python" ]

# Command to run the Flask application.
CMD ["ModelScoringService.py"]