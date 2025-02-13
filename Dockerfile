# Use a base image with JDK
# Use a base image with JDK
FROM maven:3.9.9-eclipse-temurin-21-alpine AS build

# Set the working directory
WORKDIR /app
COPY . /app
# Install Maven if required
 RRUN apk update && apk add -y maven

# Build the application
RUN mvn clean install -DskipTests

FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

# CMD [ "java", "-jar", "app.jar" ]

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
