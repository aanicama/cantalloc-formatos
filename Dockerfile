# Etapa 1: Construcción de la aplicación
FROM gradle:8.6-jdk17 AS build
WORKDIR /app

# Copiar los archivos del proyecto
COPY --chown=gradle:gradle . .

# Compilar la aplicación
RUN gradle clean build --no-daemon

# Etapa 2: Imagen final con JDK liviano
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Copiar el JAR generado desde la etapa de compilación
COPY --from=build /app/build/libs/*.jar app.jar

# Exponer el puerto de la aplicación
EXPOSE 8084

# Ejecutar la aplicación
CMD ["java", "-jar", "app.jar"]