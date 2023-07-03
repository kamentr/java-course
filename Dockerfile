# Use the official gradle image as the base image
FROM gradle:7.6.1-jdk17 AS build

# Copy the source code to the container
COPY --chown=gradle:gradle . /home/gradle/src

# Set the working directory
WORKDIR /home/gradle/src

# Run the gradle build task
RUN gradle build --no-daemon

# Use the official openjdk image as the runtime image
FROM openjdk:17-jdk-alpine

# Copy the jar file from the build image to the runtime image
COPY --from=build /home/gradle/src/build/libs/*.jar /app.jar

# Expose port 8080 for web access
EXPOSE 8080

# Run the jar file
ENTRYPOINT ["java","-jar","/app.jar"]