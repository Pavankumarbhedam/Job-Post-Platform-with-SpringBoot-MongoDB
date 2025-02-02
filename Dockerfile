# Use the latest Maven with OpenJDK 17
FROM maven:3.8.8-eclipse-temurin-17 AS build
WORKDIR /app

# Copy the source code and build the application
COPY . .
RUN mvn clean package -DskipTests

# Use OpenJDK as the final runtime image
FROM eclipse-temurin:17-jdk
WORKDIR /app

# Copy the built JAR file from the build stage
COPY --from=build /app/target/*.jar app.jar

# Command to run the application
CMD ["java", "-jar", "app.jar"]
