# Use the official Maven image to build your application
FROM maven:3.6.3-jdk-11 as build

# Copy the project files to the container
COPY . /home/app

# Set the working directory
WORKDIR /home/app

# Build the application
RUN mvn clean package

# Use the official OpenJDK image to run your application
FROM openjdk:11-jre-slim

# Copy the built jar file from the build stage
COPY --from=build /home/app/target/*.jar /usr/local/lib/app.jar

# Expose the port your app runs on
EXPOSE 8080

# Command to run your app
CMD ["java", "-jar", "/usr/local/lib/app.jar"]
