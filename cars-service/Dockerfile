FROM maven:3.8.4-openjdk-17 as builder

WORKDIR /app

COPY pom.xml .

COPY cars-service ./cars-service

WORKDIR /app/cars-service

RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim

WORKDIR /app

COPY --from=builder /app/cars-service/target/*.jar app.jar

ENTRYPOINT ["java","-jar","app.jar"]