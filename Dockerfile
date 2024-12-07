# Use an official JDK image as the base
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the Maven wrapper files and project source code into the container
COPY . .

# Build the Spring Boot application (compile the project and create the JAR)
RUN ./mvnw clean package -DskipTests

# Copy the packaged JAR file into the container
RUN mv target/*.jar app.jar

# Expose the port your app runs on
EXPOSE 8080

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]
