
# filepath: c:\Users\jamar\UMA\Año 4\Segundo cuatrimestre\TFG\backend\Dockerfile
# Etapa 1: Compilar el proyecto
FROM maven:3.9.0-eclipse-temurin-17 AS builder
WORKDIR /app
COPY pom.xml ./
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn package -DskipTests

# Etapa 2: Imagen final
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=builder /app/target/tfg-backend-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]


