FROM openjdk:11-jdk-slim as builder
COPY / /tmp
RUN cd /tmp && ./gradlew clean build -x test

FROM openjdk:11-jdk-slim
COPY --from=builder /tmp/build/libs/event-service-0.0.1-SNAPSHOT.jar /app.jar
ENTRYPOINT exec java -jar /app.jar
