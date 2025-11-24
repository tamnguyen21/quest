

#RUN mvn clean verify sonar:sonar \
#  -Dsonar.projectKey=quest-boot \
#  -Dsonar.projectName='quest-boot' \
#  -Dsonar.host.url=http://localhost:9000 \
#  -Dsonar.token=sqp_26da2428edd263448fa4b3edf90f5180f7192c43

# RUN rm -R src
# RUN rm pom.xml
# RUN mv target/quest-boot-0.0.1-SNAPSHOT.jar quest-boot.jar
# RUN rm -R target

FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY ./target/*.war quest-boot.war

CMD [ "java", "-jar", "quest-boot.war" ]

EXPOSE 8080
