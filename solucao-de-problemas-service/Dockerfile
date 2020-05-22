FROM openjdk:12-jdk-alpine AS builder
WORKDIR /usr/local/src/solucao-de-problemas-service/
COPY ./gradlew ./gradlew
COPY ./gradle/ ./gradle/
RUN ./gradlew
COPY . .
RUN ./gradlew clean build

FROM openjdk:12-jdk-alpine
WORKDIR /usr/local/bin/
COPY --from=builder /usr/local/src/solucao-de-problemas-service/build/libs/solucao-de-problemas-service.jar .
EXPOSE 8080
ENTRYPOINT ["java","-jar","solucao-de-problemas-service.jar"]
