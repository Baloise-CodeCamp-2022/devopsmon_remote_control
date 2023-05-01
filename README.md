[![CI](https://github.com/Baloise-CodeCamp-2022/devopsmon_remote_control/actions/workflows/ci.yml/badge.svg)](https://github.com/Baloise-CodeCamp-2022/devopsmon_remote_control/actions/workflows/ci.yml)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=Baloise-CodeCamp-2022_devopsmon_remote_control&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=Baloise-CodeCamp-2022_devopsmon_remote_control)
[![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=Baloise-CodeCamp-2022_devopsmon_remote_control&metric=reliability_rating)](https://sonarcloud.io/summary/new_code?id=Baloise-CodeCamp-2022_devopsmon_remote_control)
[![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=Baloise-CodeCamp-2022_devopsmon_remote_control&metric=vulnerabilities)](https://sonarcloud.io/summary/new_code?id=Baloise-CodeCamp-2022_devopsmon_remote_control)
[![Vulnerabilities](https://lift.sonatype.com/api/badge/github.com/Baloise-CodeCamp-2022/devopsmon_remote_control)](https://lift.sonatype.com/results/github.com/Baloise-CodeCamp-2022/devopsmon_remote_control/)

# Devops Monitoring - Remote Controle
Basic repository for devops monitoring remote control

## devopsmon-gate
Can be accessed using http://localhost:8080/swagger-ui/index.html.

# devopsmon-controller
[Drools](https://docs.drools.org/8.29.0.Final/drools-docs/docs-website/drools/introduction/index.html)

## docker-compose
Provides required environment with all components for the project (e.g., Kafka)
A good reference can be found at https://github.com/confluentinc/kafka-images/tree/master/examples.

### Security
- There is no Security
- This setup works with PLAINTEXT and not SSL regarding Kafka.

### Scaling
- As this is a very simple setup, you should be careful. Don't test asynchronous or performance aspects with this setup.

### Start up
Navigate to the repo root folder and execute:
```
docker-compose up --force-recreate -V
```
> --force-recreate -V will recreate everything from scratch

The local Kafka environment will be started. You can check the state with
```
$ docker-compose ps


     Name                   Command                State                            Ports
---------------------------------------------------------------------------------------------------------------
akhq              docker-entrypoint.sh ./akhq      Up             0.0.0.0:9000->9000/tcp
kafka             /etc/confluent/docker/run        Up             0.0.0.0:29092->29092/tcp, 0.0.0.0:9092->9092/tcp
schema-registry   /etc/confluent/docker/run        Up             0.0.0.0:9012->9012/tcp
zookeper          /etc/confluent/docker/run        Up             0.0.0.0:2181->2181/tcp, 2888/tcp, 3888/tcp

```

You can test using the URL: http://localhost:9000

After this first initial start you also can start the system as follows:
```
docker-compose up -V
```

### Services (Details)

### Development
To `exec` into a container, for example Kafka, user this command:
```
docker-composer exec kafka sh
```

If you like to use the Kafka or Zookeeper binary tools you have to download them here:
- [Kafka](https://kafka.apache.org/downloads)
- [Zookeeper](https://zookeeper.apache.org/releases.html)

# Process during Code Camp

- We wanted to use http://micrometer.io to create metrics and use them in kafka to react on them
  - Currently there are no easy/out of the box ways to log the data to a kafka topic (see: https://micrometer.io/docs)
  - Thats why we did some research and recognized that logging to a tool like prometheus would not fit our needs bc:
    - We need create Kafka Messages to automatically react on them (e.g. an application should listen on them and trigger other actions like restarts)
  - Agreed on :
    - Doing the aggregation of the logging on our own with kafka streams
    - Not use micrometer since we can not easily log into kafka

# Micrometer & Prometheus

We build a small demo application to test the integration of micrometer and prometheus. 
The application is a simple spring boot application which exposes a rest endpoint to 
trigger a counter. The counter is exposed to prometheus via micrometer. 
The application is build as docker during the maven build process and started in the 
docker compose call.

The alert we want to raise is based on custom metrics which are exposed via micrometer.
```promQL
max by (instance) (greeting_time_seconds) >= 3 and max by (instance) (custom_fake_cpu_usage) >= 75
```

## Alertmanager
A useful tool to view config or test alerting routes is alertmanagers
[amtool](https://github.com/prometheus/alertmanager#amtool).
Connect to running container in interactive mode to make use of it.

Examples:
```
amtool config routes show --alertmanager.url http://localhost:9093

amtool alert add this-is-a-test-alert --alertmanager.url http://localhost:9093
```

# References
- [Confluentic Info][Confluentic Info]
- [Kafka Connect REST API][Kafka Connect REST API]
- [Schema Registry REST API][Schema Registry REST API]

[Confluentic Info]: https://docs.confluent.io/current/tutorials/build-your-own-demos.html?utm_source=github&utm_medium=demo&utm_campaign=ch.examples_type.community_content.cp-all-in-one
[Kafka Connect REST API]: https://docs.confluent.io/current/connect/references/restapi.html
[Schema Registry REST API]: https://docs.confluent.io/current/schema-registry/develop/api.html

### Spring Reference Documentation

For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.7.4/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.7.4/maven-plugin/reference/html/#build-image)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/2.7.4/reference/htmlsingle/#using.devtools)
* [Spring for Apache Kafka](https://docs.spring.io/spring-boot/docs/2.7.4/reference/htmlsingle/#messaging.kafka)
* [Spring Boot Actuator](https://docs.spring.io/spring-boot/docs/2.7.4/reference/htmlsingle/#actuator)
* [Apache Kafka Streams Support](https://docs.spring.io/spring-kafka/docs/current/reference/html/#streams-kafka-streams)
* [Apache Kafka Streams Binding Capabilities of Spring Cloud Stream](https://docs.spring.io/spring-cloud-stream/docs/current/reference/htmlsingle/#_kafka_streams_binding_capabilities_of_spring_cloud_stream)

### Spring Guides

The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service with Spring Boot Actuator](https://spring.io/guides/gs/actuator-service/)
* [Samples for using Apache Kafka Streams with Spring Cloud stream](https://github.com/spring-cloud/spring-cloud-stream-samples/tree/master/kafka-streams-samples)

