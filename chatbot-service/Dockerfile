FROM openjdk:12-jdk-alpine AS builder
WORKDIR /usr/local/src/chatbot-service/
COPY ./gradlew ./gradlew
COPY ./gradle/ ./gradle/
RUN ./gradlew
COPY . .
RUN ./gradlew clean build

FROM openjdk:12-jdk-alpine
WORKDIR /usr/local/bin/
COPY --from=builder /usr/local/src/chatbot-service/build/libs/chatbot-service.jar .
EXPOSE 8080
ENTRYPOINT ["java","-jar","chatbot-service.jar"]
