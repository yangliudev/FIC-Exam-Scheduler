FROM maven:3.8.5-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests


FROM openjdk:17-jdk-slim
COPY --from=build /target/gp-0.0.1-SNAPSHOT.jar gp.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","gp.jar"]