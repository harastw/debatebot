# Dockerfile (лучше положить в dialog-spring/)

FROM eclipse-temurin:8-jre

WORKDIR /app

# Копируем JAR из сборки
COPY target/dialog-spring-1.0.0.jar app.jar

ENV SPRING_PROFILES_ACTIVE=docker

# Запуск
ENTRYPOINT ["java", "-jar", "app.jar"]