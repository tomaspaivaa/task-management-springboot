# Etapa 1: Construir o projeto com Maven
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Etapa 2: Executar o JAR gerado
FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY --from=build /app/target/tasks-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
