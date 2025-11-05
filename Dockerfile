# Use OpenJDK 17 as base image
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy source code
COPY src/ ./src/

# Copy lib directory if it contains dependencies
COPY lib/ ./lib/

# Create bin directory for compiled classes
RUN mkdir -p bin

# Compile Java source files recursively
RUN find src -name "*.java" -exec javac -cp "lib/*" -d bin {} +

# Expose port
EXPOSE 4000

# Run the application
CMD ["java", "-cp", "bin:lib/*", "App"]
