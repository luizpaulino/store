FROM amazoncorretto:17-alpine-jdk
LABEL authors="Luiz"

# Set the working directory in the container
WORKDIR /app

# Copy the application JAR file into the container
COPY build/libs/payment-1.0.jar /app/payment.jar

# Copie os recursos da aplicação para o contêiner
COPY src/main/resources/ /app/

# Expose the port your application runs on
EXPOSE 8083

# Command to run the application
CMD ["sh", "-c", "java -jar -Dspring.profiles.active=dev payment.jar"]
