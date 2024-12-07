# Use an official JDK image as the base
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy Maven wrapper and pom.xml into the container
COPY .mvn/ .mvn
COPY mvnw .
COPY pom.xml .

# Download dependencies to speed up subsequent builds
RUN ./mvnw dependency:go-offline -B

# Copy the source code into the container
COPY src ./src

# Build the JAR file
RUN ./mvnw clean package -DskipTests

# Copy the packaged JAR file to the final location
RUN mv target/*.jar app.jar

# Expose the port your app runs on
EXPOSE 8080

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]
