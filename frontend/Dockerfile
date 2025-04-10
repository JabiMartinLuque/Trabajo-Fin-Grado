# Etapa 1: Builder
FROM node:20.12.0-alpine AS builder

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia los archivos de configuración y package.json para instalar las dependencias
COPY package*.json ./

# Instala las dependencias de npm
RUN npm install

# Copia el resto del código fuente al contenedor
COPY . .

# Genera la build de producción
RUN npm run build -- --configuration production

# Etapa 2: Servir con Nginx
FROM nginx:stable-alpine

RUN rm /etc/nginx/conf.d/default.conf

# Copia tu archivo de configuración de Nginx personalizado (si tienes uno)
COPY nginx.conf /etc/nginx/conf.d

# Copia la build generada en la etapa anterior al directorio por defecto de Nginx
# Asegúrate de ajustar "frontend" si tu outputPath en angular.json es diferente.
# Copia la carpeta browser
COPY --from=builder /app/dist/frontend/browser /usr/share/nginx/html

# Renombra index.csr.html a index.html
RUN mv /usr/share/nginx/html/index.csr.html /usr/share/nginx/html/index.html

# Expone el puerto 80 para acceder a la aplicación
EXPOSE 80

# Comando para iniciar Nginx en primer plano
CMD ["nginx", "-g", "daemon off;"]

