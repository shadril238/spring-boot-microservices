# Start with a base image containing Java runtime (openjdk:17-jdk-slim)
FROM openjdk:17-jdk-slim

# Information around who maintains the image
MAINTAINER shadril238

# Add the application's jar to the image
COPY target/account-service-0.0.1-SNAPSHOT.jar account-service-0.0.1-SNAPSHOT.jar

# Execute the application
ENTRYPOINT ["java", "-jar", "account-service-0.0.1-SNAPSHOT.jar"]


# CLI commands to build the image
# 1. docker build . -t shadril238/account-service:s4 (create the image)
# 2. docker images
# 3. docker inspect image <image_id> (to check the image)
# 4. docker run -d -p 8080:8080 shadril238/account-service:s4 (to run the container)
# 5. docker ps (to check the running container)
# 6. docker stop <container_id> (to stop the container)
