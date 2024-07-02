FROM maven:3.8.8-eclipse-temurin-17 AS Builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
COPY src/main/resources/ /app/src/main/resources/
RUN mvn clean package -DskipTests


FROM eclipse-temurin:17-jre
WORKDIR /app
VOLUME /tmp
COPY --from=Builder /app/target/*.jar /app/application.jar
CMD ["java", "-jar", "application.jar"]