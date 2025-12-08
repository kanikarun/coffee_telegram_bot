# Use official OpenJDK image
FROM eclipse-temurin:17-jdk-alpine

# Set working directory
WORKDIR /app

# Copy built JAR from local build
COPY build/libs/coffee-shop-html-telegram-bot-0.0.1-SNAPSHOT.jar app.jar

# Expose port (optional, Render will use $PORT)
EXPOSE 8080

# Run the app
ENTRYPOINT ["java","-jar","/app/app.jar"]
