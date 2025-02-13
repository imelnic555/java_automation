# Use Maven with OpenJDK 17 as the base image
FROM maven:3.8.6-openjdk-17 AS builder

# Set working directory inside container
WORKDIR /app

# Copy Maven dependencies (for caching)
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the entire project
COPY . .

# Build the project
RUN mvn clean package -DskipTests

# Use a lightweight JDK image to run tests
FROM openjdk:17-jdk-slim
WORKDIR /app

# Copy built files from the previous stage
COPY --from=builder /app/target/*.jar app.jar

# Install Chrome and ChromeDriver
RUN apt-get update && apt-get install -y wget unzip curl \
    && wget -q -O - https://dl-ssl.google.com/linux/linux_signing_key.pub | apt-key add - \
    && echo "deb http://dl.google.com/linux/chrome/deb/ stable main" > /etc/apt/sources.list.d/google-chrome.list
