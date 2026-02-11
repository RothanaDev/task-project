# ====== Build stage ======
FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /app

COPY pom.xml .
RUN mvn -B -DskipTests dependency:go-offline

COPY src ./src
RUN mvn -B -DskipTests clean package

# ====== Run stage ======
FROM eclipse-temurin:21-jre
WORKDIR /app

COPY --from=build /app/target/taskflow-api-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 10000

ENTRYPOINT ["sh", "-c", "java -Xmx384m -Xss512k -XX:MaxMetaspaceSize=128m -Dserver.port=${PORT:-10000} -jar app.jar"]
