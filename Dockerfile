# Start with a JDK 17 image for the build stage
FROM openjdk:17-slim as build

# Install Maven
RUN apt-get update && \
    apt-get install -y maven && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

# Copy the source code
COPY src /home/app/src
# Copy the Maven configuration file
COPY pom.xml /home/app

# Set the working directory in the container
WORKDIR /home/app

# Package the application
RUN mvn clean package

# Define the runtime stage
FROM openjdk:17-slim as runtime

# Set the working directory in the container
WORKDIR /app

# Install Python
RUN apt-get update && \
    apt-get install -y python3 python3-pip libpq-dev && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

# Copy the Python requirements file
COPY requirements.txt /app/

# Install the required Python libraries from requirements.txt
RUN pip3 install --no-cache-dir -r requirements.txt

# Copy the JAR file from the build stage
COPY --from=build /home/app/target/*.jar /app/app.jar

# Expose the port the app uses
EXPOSE 8080

# Command to run the app
CMD java -jar app.jar --server.port=$PORT
