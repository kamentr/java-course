# Build stage
FROM openjdk:17-jdk-alpine3.14 AS build

WORKDIR /app

COPY build.gradle gradlew ./
COPY gradle ./gradle

COPY . .
RUN ./gradlew build -x test

# Run stage
FROM eclipse-temurin:17-jre-alpine
COPY --from=build /app/build/libs/*.jar app.jar

CMD ["java", "-jar", "app.jar"]