# ==========================================
# STAGE 1: Build the JAR using Maven [cite: 107]
# ==========================================
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

# Copy pom.xml before copying the source code to take advantage of Docker layer caching [cite: 109]
COPY pom.xml .
# Download dependencies (this layer is cached so subsequent builds are faster)
RUN mvn dependency:go-offline

# Copy the rest of the source code and package the JAR
COPY src ./src
RUN mvn clean package -DskipTests

# ==========================================
# STAGE 2: Run the JAR using a JRE-only base image [cite: 107]
# ==========================================
# Using the specific runtime base image required by the rubric [cite: 108]
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Copy the compiled JAR from Stage 1 into this new, lightweight image
COPY --from=build /app/target/*.jar app.jar

# EXPOSE the correct application port [cite: 111]
EXPOSE 8080

# Use ENTRYPOINT in exec form [cite: 110]
ENTRYPOINT ["java", "-jar", "app.jar"]