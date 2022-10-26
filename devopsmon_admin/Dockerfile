FROM openjdk:17
ARG JAR_FILE
COPY ${JAR_FILE} application.jar
EXPOSE 8080/tcp
ENTRYPOINT java -jar /application.jar
