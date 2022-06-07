FROM maven:3.8.5-openjdk-8 AS MAVEN_BUILD

COPY ./ ./

RUN mvn clean package


FROM openjdk:8-jre

COPY --from=MAVEN_BUILD /target/Project1-1.0-SNAPSHOT.jar project1.jar

CMD ["java","-jar", "project1.jar"]
