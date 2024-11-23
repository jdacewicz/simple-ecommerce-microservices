FROM maven:3.9.9-eclipse-temurin-21-alpine AS builder

WORKDIR /opt/app
COPY pom.xml ./
RUN mvn dependency:go-offline
COPY src ./src

RUN mvn clean package -DskipTests

RUN java -Djarmode=layertools -jar target/*.jar extract

FROM eclipse-temurin:21-jre-alpine

RUN apk --no-cache add bash curl

RUN mkdir /app
RUN addgroup -S spring && adduser -S spring -G spring
WORKDIR /app

COPY --from=builder /opt/app/dependencies/ ./
COPY --from=builder /opt/app/spring-boot-loader/ ./
COPY --from=builder /opt/app/snapshot-dependencies/ ./
COPY --from=builder /opt/app/application/ ./

RUN chown -R spring:spring .
USER spring:spring

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-cp", "BOOT-INF/classes:BOOT-INF/lib/*", "dev.jakubdacewicz.order_service.OrderServiceApplication"]