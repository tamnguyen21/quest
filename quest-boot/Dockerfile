FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY ./target/*.war quest-boot.war

CMD [ "java", "-jar", "quest-boot.war" ]

EXPOSE 8080
