FROM openjdk:17
COPY devopsmon-gate/target/*.jar application.jar
EXPOSE 8080/tcp
ENTRYPOINT java -jar /application.jar
