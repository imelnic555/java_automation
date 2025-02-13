# Stage 1: Build the Java Project
FROM maven:3.8.7-eclipse-temurin-17-alpine AS builder

WORKDIR /app

# Copy Maven configuration and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the entire project source code
COPY . .

# Build the project (skipping tests during build)
RUN mvn clean package -DskipTests

# Stage 2: Run Tests in a Lightweight JDK Container
FROM maven:3.8.7-eclipse-temurin-17-alpine AS runtime

WORKDIR /app

# Copy the built application from the builder stage
COPY --from=builder /app/target/*.jar app.jar

# Install Chrome and ChromeDriver
RUN apt-get update && apt-get install -y wget unzip curl \
    && wget -q -O - https://dl-ssl.google.com/linux/linux_signing_key.pub | apt-key add - \
    && echo "deb http://dl.google.com/linux/chrome/deb/ stable main" > /etc/apt/sources.list.d/google-chrome.list \
    && apt-get update && apt-get install -y google-chrome-stable \
    && wget https://chromedriver.storage.googleapis.com/$(curl -sS chromedriver.storage.googleapis.com/LATEST_RELEASE)/chromedriver_linux64.zip \
    && unzip chromedriver_linux64.zip \
    && mv chromedriver /usr/local/bin/ \
    && chmod +x /usr/local/bin/chromedriver \
    && rm chromedriver_linux64.zip

# Set ChromeDriver Path
ENV PATH="/usr/local/bin:${PATH}"
ENV webdriver.chrome.driver="/usr/local/bin/chromedriver"

# Run tests when the container starts
CMD ["mvn", "test"]
