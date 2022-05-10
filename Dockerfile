FROM maven:3.8.5-openjdk-18 as builder
COPY src /app/src
COPY pom.xml /app
RUN mvn -f /app/pom.xml clean package

FROM openjdk:18
COPY --from=builder  /app/target/app-*.jar /app/src/app.jar
ENTRYPOINT ["/usr/bin/java", "-jar", "/app/src/app.jar"]
EXPOSE 8080
