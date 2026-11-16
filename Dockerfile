# Stage 1: Build da aplicação com Maven e JDK 21 LTS
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Runtime leve com JRE 21 LTS
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app
COPY --from=build /app/target/zelourbano-1.0.0.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]