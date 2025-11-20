FROM maven:3.9.11-eclipse-temurin-21

WORKDIR /app

COPY pom.xml .

COPY ./src ./src

RUN mvn package

CMD [ "java", "-jar", "target/quest-boot-0.0.1-SNAPSHOT.jar" ]

EXPOSE 8080
