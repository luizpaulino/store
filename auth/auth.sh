#!/bin/bash
echo "Building Gradle project..."
./gradlew build

# Build Docker image
echo "Building Docker image..."
docker build -t auth .

# Start Docker Compose services
echo "Starting Docker Compose services..."
docker-compose stop
docker-compose up -d
