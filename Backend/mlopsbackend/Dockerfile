# Use the official Maven image as a parent image
FROM maven:3.8.5-openjdk-17 AS build
#FROM openjdk:17-python3.8 AS build
WORKDIR /app

# Copy the pom.xml and src files into the container
COPY ./pom.xml ./pom.xml
COPY ./src ./src

# Build the application
RUN mvn clean package

# Create the shared_data directory and copy model.pkl into it
RUN mkdir -p /shared_data
COPY ./src/main/java/com/backend/mlopsbackend/Data/model.pkl /shared_data/model.pkl

# Create a separate image for running the Spring Boot application
FROM openjdk:17
WORKDIR /app

# Copy the JAR file from the build stage to the new image
COPY --from=build app/target/mlopsbackend-0.0.1-SNAPSHOT.jar TFG-MLOps.jar

# Copy the model.pkl file from the build stage
COPY --from=build /shared_data/model.pkl /shared_data/model.pkl

# Make port 8080 available to the world outside this container
EXPOSE 8080

# Run the Spring Boot application when the container launches
ENTRYPOINT ["java", "-jar", "TFG-MLOps.jar"]