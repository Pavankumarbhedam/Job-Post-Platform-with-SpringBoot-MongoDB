# Use Maven with JDK 21 for the build stage
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

# Copy the source code and build the application
COPY . .
RUN mvn clean package -DskipTests

# Use OpenJDK 21 for the final runtime image
FROM eclipse-temurin:21-jdk
WORKDIR /app

# Copy the built JAR file from the build stage
COPY --from=build /app/target/*.jar app.jar

# Command to run the application
CMD ["java", "-jar", "app.jar"]
