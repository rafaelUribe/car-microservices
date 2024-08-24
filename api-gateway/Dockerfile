FROM amazoncorretto:22.0.2-alpine
LABEL authors="RafaelUribe"

# RUN ./mvnw install

WORKDIR /usr/src/storyshelf
COPY target/api-gateway-0.0.1.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]