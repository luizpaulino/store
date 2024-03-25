
#! Startup script for the Items service

cd items

#!/bin/bash
echo "Building Gradle project..."
./gradlew build

# Build Docker image
echo "Building Docker image..."
docker build -t items .

# Start Docker Compose services
echo "Starting Docker Compose services..."
docker-compose stop
docker-compose up -d

cd ..

#! Startup script for the Auth service

cd auth

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

cd ..

#! Startup script for the Cart service

cd cart

#!/bin/bash
echo "Building Gradle project..."
./gradlew build

# Build Docker image
echo "Building Docker image..."
docker build -t cart .

# Start Docker Compose services
echo "Starting Docker Compose services..."
docker-compose stop
docker-compose up -d

cd ..

#! Startup script for the Payment service

cd payment

#!/bin/bash
echo "Building Gradle project..."
./gradlew build

# Build Docker image
echo "Building Docker image..."
docker build -t payment .

# Start Docker Compose services
echo "Starting Docker Compose services..."
docker-compose stop
docker-compose up -d

cd ..
