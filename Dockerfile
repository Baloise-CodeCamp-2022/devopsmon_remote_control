FROM openjdk:17
COPY devopsmon_admin/target/*.jar application.jar
EXPOSE 8080/tcp
ENTRYPOINT java -jar /application.jar
