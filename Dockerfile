FROM openjdk:17-jdk-alpine3.14

WORKDIR /app

COPY build.gradle gradlew ./
COPY gradle ./gradle
RUN ./gradlew --version

COPY . .
RUN ./gradlew build

CMD ["java", "-jar", "build/libs/*.jar"]
