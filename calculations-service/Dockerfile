# Stage 1: Build the application
FROM maven:3.9.9 AS build

# Set the working directory
WORKDIR /app

# Copy the pom.xml and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the source code
COPY src ./src

# Package the application
RUN mvn package -DskipTests

# Stage 2: Run the application
FROM openjdk:21-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /app/target/calculations-service-0.0.1-SNAPSHOT.jar app.jar

# Expose the port (adjust if your application uses a different port)
#EXPOSE 8080

# Run the application
# ENTRYPOINT ["java", "-jar", "app.jar"]