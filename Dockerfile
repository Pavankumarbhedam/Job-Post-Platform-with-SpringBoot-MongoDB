# Use Maven to build the Spring Boot application
FROM maven:3.8.6-openjdk-17 AS build
WORKDIR /app

# Copy the source code and build the application
COPY . .
RUN mvn clean package -DskipTests

# Use OpenJDK as the final runtime image
FROM openjdk:17
WORKDIR /app

# Copy the built JAR file from the build stage
COPY --from=build /app/target/*.jar app.jar

# Command to run the application
CMD ["java", "-jar", "app.jar"]
