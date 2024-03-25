FROM amazoncorretto:17-alpine-jdk
LABEL authors="Luiz"

# Set the working directory in the container
WORKDIR /app

# Copy the application JAR file into the container
COPY build/libs/cart-1.0.jar /app/cart.jar
COPY src/main/resources/ /app/

# Expose the port your application runs on
EXPOSE 8082

# Command to run the application
CMD ["sh", "-c", "java -jar -Dspring.profiles.active=dev cart.jar"]
