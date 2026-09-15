FROM dhi.io/maven:3-jdk25-alpine-dev AS build

WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY . .
RUN mvn package -DskipTests

FROM dhi.io/eclipse-temurin:25-alpine

WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
