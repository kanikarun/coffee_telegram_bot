# Stage 1: Build the JAR
FROM eclipse-temurin:21-jdk-alpine AS build
WORKDIR /app

COPY . .

# Make gradlew executable
RUN chmod +x gradlew

# Build jar
RUN ./gradlew clean bootJar

# Stage 2: Run the app
FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app

COPY --from=build /app/build/libs/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
