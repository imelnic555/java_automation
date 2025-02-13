# Use a valid Maven image with JDK 17
FROM eclipse-temurin:17 as jre-build

# Set working directory inside the container
WORKDIR /app

# Copy Maven dependencies (for caching)
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the entire project
COPY . .

# Build the project
RUN mvn clean package -DskipTests

# Use a lightweight Java runtime for execution
FROM eclipse-temurin:17-jdk-slim AS runtime
WORKDIR /app

# Copy built files from the previous stage
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

# Run tests
CMD ["mvn", "test"]
