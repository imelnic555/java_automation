# Use an official Java runtime as a parent image
FROM maven:3.8.6-openjdk-17

# Set the working directory inside the container
WORKDIR /app

# Copy all project files into the container
COPY . .

# Install dependencies
RUN apt-get update && apt-get install -y wget unzip curl \
    && wget -q -O - https://dl-ssl.google.com/linux/linux_signing_key.pub | apt-key add - \
    && echo "deb http://dl.google.com/linux/chrome/deb/ stable main" > /etc/apt/sources.list.d/google-chrome.list \
    && apt-get update && apt-get install -y google-chrome-stable \
    && wget https://chromedriver.storage.googleapis.com/$(curl -sS chromedriver.storage.googleapis.com/LATEST_RELEASE)/chromedriver_linux64.zip \
    && unzip chromedriver_linux64.zip \
    && mv chromedriver /usr/local/bin/ \
    && chmod +x /usr/local/bin/chromedriver \
    && rm chromedriver_linux64.zip

# Set environment variables for ChromeDriver
ENV PATH="/usr/local/bin:${PATH}"
ENV webdriver.chrome.driver="/usr/local/bin/chromedriver"

# Run Maven tests
CMD ["mvn", "test"]
