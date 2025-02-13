# Stage 1: Build the Java Project with Maven
FROM maven:3.9.9-eclipse-temurin-21-alpine AS build

# Set the working directory
WORKDIR /app

# Copy project files
COPY . /app

# Build the application
RUN mvn clean install -DskipTests

# Stage 2: Runtime (Use JDK, Not Just JRE)
FROM eclipse-temurin:21-jdk-alpine AS runtime

WORKDIR /app

# Install Maven in the runtime container
RUN apk update && apk add --no-cache maven bash curl unzip chromium chromium-chromedriver xvfb

# Copy the built JAR file from the build stage
COPY --from=build /app/target/*.jar app.jar

# Set ChromeDriver Path
ENV PATH="/usr/bin:${PATH}"
ENV webdriver.chrome.driver="/usr/bin/chromedriver"

# Verify installations
RUN mvn -version && chromium-browser --version && chromedriver --version

# Run tests when the container starts
CMD ["mvn", "test"]
