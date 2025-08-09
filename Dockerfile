# ===== Stage 1: Build the JAR =====
FROM maven:3.9.9-eclipse-temurin-21 AS builder

# Set working directory
WORKDIR /app

# Copy Maven configuration and pre-download dependencies for faster builds
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy source code and build the application
COPY src ./src
RUN mvn clean package -DskipTests

# ===== Stage 2: Run the application =====
FROM eclipse-temurin:21-jdk-jammy

WORKDIR /app

# Copy the built JAR from the builder stage
COPY --from=builder /app/target/*.jar app.jar

# Expose Spring Boot's default port
EXPOSE 8080

# Command to run the app
ENTRYPOINT ["java", "-jar", "app.jar"]
