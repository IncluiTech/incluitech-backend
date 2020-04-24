FROM openjdk:12-jdk-alpine AS builder
COPY . /usr/local/src/chatbot-service/
WORKDIR /usr/local/src/chatbot-service/
RUN ./gradlew clean build

FROM openjdk:12-jdk-alpine
WORKDIR /usr/local/bin/
COPY --from=builder /usr/local/src/chatbot-service/build/libs/chatbot-service.jar .
EXPOSE 8080
ENTRYPOINT ["java","-jar","chatbot-service.jar"]
