# Use an official JDK image as the base
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the Maven wrapper scripts and configuration
COPY .mvn/ .mvn
COPY mvnw .

# Set execute permission for the Maven wrapper script
RUN chmod +x mvnw

# Copy the Maven POM file
COPY pom.xml .

# Download dependencies to speed up subsequent builds
RUN ./mvnw dependency:go-offline -B

# Copy the source code into the container
COPY src/ src/

# Package the application
RUN ./mvnw package -DskipTests

# Expose the port your app runs on
EXPOSE 8080

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "target/jobportal-0.0.1-SNAPSHOT.jar"]
