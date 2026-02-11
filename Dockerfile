# ====== Build stage ======
FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /app

# Copy pom first (cache dependencies)
COPY pom.xml .
RUN mvn -B -DskipTests dependency:go-offline

# Copy source code
COPY src ./src

# Build jar
RUN mvn -B -DskipTests clean package


# ====== Run stage ======
FROM eclipse-temurin:21-jre
WORKDIR /app

# Ensure we only copy the actual executable jar, not the -plain.jar
# We use a wildcard and then find the one that isn't 'plain'
COPY --from=build /app/target/taskflow-api-0.0.1-SNAPSHOT.jar app.jar

# Render uses its own PORT, so we tell Spring to use it
ENV SERVER_PORT=8080
EXPOSE 8080

# Limit memory to 384MB to leave room for the OS/Metaspace
ENTRYPOINT ["java", "-Xmx384m", "-Xss512k", "-XX:MaxMetaspaceSize=128m", "-Dserver.port=${PORT:-8080}", "-jar", "app.jar"]


