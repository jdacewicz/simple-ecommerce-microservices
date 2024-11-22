FROM maven:3.9.9-eclipse-temurin-21-alpine AS builder

WORKDIR /opt/app
COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre-alpine

WORKDIR /opt/app
COPY --from=builder /opt/app/target/*.jar /opt/app/*.jar

EXPOSE 8083
CMD ["java", "-jar", "/opt/app/*.jar"]