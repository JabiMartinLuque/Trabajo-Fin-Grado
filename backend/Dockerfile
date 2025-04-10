# Utiliza una imagen base ligera de OpenJDK 17 (ajusta la versión si es necesario)
FROM openjdk:17-jdk-slim

# Establece el directorio de trabajo (opcional)
WORKDIR /app

# Copia el archivo JAR generado en la carpeta target al directorio de trabajo en el contenedor
COPY target/tfg-backend-0.0.1-SNAPSHOT.jar app.jar

# Expone el puerto 8080 (asegúrate que en tu aplicación este sea el puerto configurado)
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]

