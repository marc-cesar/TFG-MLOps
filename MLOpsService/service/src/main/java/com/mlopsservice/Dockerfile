# Use the official Maven image as a parent image
#FROM maven:3.8.5-openjdk-17 AS build
FROM openjdk:17-python3.8 AS build
WORKDIR /app

# Copy the pom.xml and src files into the container
COPY ./pom.xml ./pom.xml
COPY ./src ./src

# Build the application
RUN mvn clean package

# Create a separate image for running the Spring Boot application
FROM openjdk:17
WORKDIR /app

# Copy the JAR file from the build stage to the new image
ADD TFG-MLOps.jar TFG-MLOps.jar

# Make port 8080 available to the world outside this container
EXPOSE 8080

# Run the Spring Boot application when the container launches
ENTRYPOINT ["java", "-jar", "TFG-MLOps.jar"]
