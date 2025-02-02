# Use official OpenJDK runtime as base image
FROM openjdk:17

# Set working directory
WORKDIR /app

# Copy the built JAR file from target/ folder to the container
COPY target/*.jar app.jar

# Command to run the application
CMD ["java", "-jar", "app.jar"]
