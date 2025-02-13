# Stage 1: Build the Java Project
FROM eclipse-temurin:21

WORKDIR /app

# Copy Maven configuration and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the entire project source code
COPY . .

# Build the project (skipping tests during build)
RUN mvn clean package -DskipTests

# Stage 2: Run Tests in a Lightweight JDK Container
FROM eclipse-temurin:21

WORKDIR /app

# Copy the built application from the builder stage
COPY --from=builder /app/target/*.jar app.jar

# Install Chrome and ChromeDriver
RUN apt-get update && apt-get install -y wget unzip curl \
    && wget -q 
