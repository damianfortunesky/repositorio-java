# Etapa 1: build del JAR con Maven
FROM maven:3.9.9-eclipse-temurin-17 AS builder

WORKDIR /app

# Copiar pom.xml y descargar dependencias (caché)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copiar código fuente y compilar
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa 2: imagen ligera para ejecutar
FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app

# Copiar el JAR generado desde la etapa anterior
COPY --from=builder /app/target/*.jar app.jar

# Puerto expuesto por Spring Boot
EXPOSE 8080

# Comando de inicio
ENTRYPOINT ["java", "-jar", "app.jar"]
