# Stage 1: Build the Java Project with Maven
FROM maven:3.8.7-eclipse-temurin-17-alpine AS builder

WORKDIR /app

# Copy Maven dependencies for caching
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy project files
COPY . .

# Build the project and check if the target/ folder is created
RUN mvn clean package -DskipTests || (echo "Maven Build Failed!" && exit 1)
RUN ls -l target/ || (echo "Target folder missing!" && exit 1)

# Stage 2: Run Tests in a Lightweight Java Container
FROM eclipse-temurin:17-jdk-alpine AS runtime

WORKDIR /app

# Copy built JAR file from the builder stage
COPY --from=builder /app/target/*.jar app.jar

# Install Chrome and ChromeDriver in Alpine
RUN apk update && apk add --no-cache \
    bash \
    curl \
    unzip \
    chromium \
    chromium-chromedriver \
    xvfb

# Set ChromeDriver Path
ENV PATH="/usr/bin:${PATH}"
ENV webdriver.chrome.driver="/usr/bin/chromedriver"

# Verify Chrome installation
RUN chromium-browser --version && chromedriver --version

# Run tests when the container starts
CMD ["mvn", "test"]
