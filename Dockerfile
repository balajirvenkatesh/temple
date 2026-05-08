# Step 1: Use an official JDK runtime as a parent image
#FROM eclipse-temurin:17-jdk-alpine
FROM eclipse-temurin:21-jre

# Step 2: Set the working directory inside the container
WORKDIR /app

# Step 3: Copy the executable JAR from your target folder to the container
# Replace 'your-app-name.jar' with the actual name found in your /target or /build/libs folder
COPY target/Temple-0.0.1-SNAPSHOT.jar app.jar

# Step 4: Expose the port your Spring Boot app runs on (usually 8080)
EXPOSE 8080

# Step 5: Run the JAR file
ENTRYPOINT ["java", "-jar", "app.jar"]