# Etapa 1: Construcción de la aplicación
FROM maven:3.8.6-openjdk-17 AS builder
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Etapa 2: Crear la imagen final con JDK
FROM openjdk:17
WORKDIR /app
COPY --from=builder /app/target/tfg_backend-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
