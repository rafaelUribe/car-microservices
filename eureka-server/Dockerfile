FROM maven:3.8.4-openjdk-17 as builder

WORKDIR /app

COPY pom.xml .

COPY eureka-server ./eureka-server

WORKDIR /app/eureka-server

RUN ls -R /app

RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim

WORKDIR /app

COPY --from=builder /app/eureka-server/target/*.jar app.jar

ENTRYPOINT ["java","-jar","app.jar"]