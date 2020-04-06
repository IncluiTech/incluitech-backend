FROM openjdk:12-jdk-alpine
EXPOSE 8080
COPY build/libs/chatbot-service.jar chatbot-service.jar
ENTRYPOINT ["java","-jar","/chatbot-service.jar"]
