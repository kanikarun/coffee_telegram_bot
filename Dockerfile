# Stage 1: Build the JAR
FROM eclipse-temurin:21-jdk-alpine AS build
WORKDIR /app

COPY . .

# Give gradlew permission
RUN chmod +x gradlew

# Build the jar
RUN ./gradlew clean bootJar

# Stage 2: Run the application
FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app

# Copy the generated jar (use wildcard to avoid exact filename issues)
COPY --from=build /app/build/libs/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
