# Use the official Python image as a parent image
FROM python:3.11.4

# Set the working directory to /app
WORKDIR /app

# Update package list and install pkg-config
RUN apt-get update && apt-get install -y pkg-config

# Copy the current directory contents into the container at /app
COPY . /app

# Update package list and install necessary libraries
RUN apt-get update && apt-get install -y \
    pkg-config \
    libsystemd-dev \
    libdbus-1-dev

# Install any needed packages specified in requirements.txt
RUN pip install -r requirements.txt


# Make port 5000 available to the world outside this container
EXPOSE 5000

# Define environment variable
ENV NAME World

# Run app.py when the container launches
#CMD ["python", "ModelScoringService.py"]
CMD ["gunicorn", "-w", "4", "-b", "0.0.0.0:5000", "ModelScoringService:app"]