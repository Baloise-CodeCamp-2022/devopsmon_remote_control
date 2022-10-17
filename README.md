# devopsmon_remote_control
Basic repository for devops monitoring remote control


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

# References
- [Confluentic Info][Confluentic Info]
- [Kafka Connect REST API][Kafka Connect REST API]
- [Schema Registry REST API][Schema Registry REST API]

[Confluentic Info]: https://docs.confluent.io/current/tutorials/build-your-own-demos.html?utm_source=github&utm_medium=demo&utm_campaign=ch.examples_type.community_content.cp-all-in-one
[Kafka Connect REST API]: https://docs.confluent.io/current/connect/references/restapi.html
[Schema Registry REST API]: https://docs.confluent.io/current/schema-registry/develop/api.html