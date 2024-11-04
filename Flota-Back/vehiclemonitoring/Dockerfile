# Usa una imagen de Maven para construir la aplicación
FROM maven:3.8.5-eclipse-temurin-22 AS build

# Configura el directorio de trabajo
WORKDIR /app

# Copia el archivo pom.xml y descarga las dependencias
COPY pom.xml .
RUN mvn dependency:go-offline

# Copia el resto del código fuente y compila la aplicación
COPY src ./src
RUN mvn clean package -DskipTests

# Usa una imagen más ligera para ejecutar la aplicación (OpenJDK 22)
FROM eclipse-temurin:22-jre

# Configura el directorio de trabajo
WORKDIR /app

# Copia el archivo .jar compilado desde la etapa anterior
COPY --from=build /app/target/vehicle-monitoring-system-0.0.1-SNAPSHOT.jar app.jar

# Expone el puerto que usará la aplicación
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
