# Stage 1: Build the Java Project with Maven
FROM maven:3.9.9-eclipse-temurin-21-alpine AS build

# Set the working directory
WORKDIR /app

# Copy the pom.xml first to cache dependencies
COPY pom.xml .

# Download dependencies without copying all files (speeds up build)
RUN mvn dependency:go-offline

# Copy the full project after dependencies are cached
COPY . .

# Build the application
RUN mvn clean install -DskipTests

# Stage 2: Runtime (Use JDK, Not Just JRE)
FROM eclipse-temurin:21-jdk-alpine AS runtime

WORKDIR /app

# Install Maven in the runtime container
RUN apk update && apk add --no-cache maven bash curl unzip chromium chromium-chromedriver xvfb

# Copy the built JAR file from the build stage
COPY --from=build /app/target/*.jar app.jar

# Copy the pom.xml file (needed for mvn test execution)
COPY --from=build /app/pom.xml .

# Set ChromeDriver Path
ENV PATH="/usr/bin:${PATH}"
ENV webdriver.chrome.driver="/usr/bin/chromedriver"

# Verify installations
RUN mvn -version && chromium-browser --version && chromedriver --version

# Run tests when the container starts
CMD ["mvn", "test"]
