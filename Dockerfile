# Use a base image with Java (adjust version as needed)
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the built JAR file into the container
COPY target/kafka-consumer-0.0.1-SNAPSHOT.jar app.jar

# Command to run the application when the container starts
ENTRYPOINT ["java", "-jar", "app.jar"]
