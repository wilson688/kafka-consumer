# kafka-consumer

## RUN LOCALLY BY USING DOCKER-COMPOSE
```dtd
 docker-compose -f docker-compose.yml up
```

### POST MESSAGES VIA PRODUCER
```dtd
docker-compose exec kafka bash
kafka-topics --create --topic test-topic --bootstrap-server localhost:9092 --partitions 1 --replication-factor 1
kafka-console-producer --broker-list localhost:9092 --topic test-topic
```

### CONSUME THE MESSAGE
```dtd
docker-compose exec kafka bash
kafka-console-consumer --bootstrap-server kafka:29092 --topic test-topic --from-beginning --group my-unique-consumer-group-$(date +%s)
```

### VALIDATE KAFKA
```dtd
kafka-topics --bootstrap-server kafka:29092 --list
kafka-topics --bootstrap-server kafka:29092 --describe --topic test-topic
kafka-run-class kafka.tools.GetOffsetShell --broker-list kafka:29092 --topic test-topic --time -1
```

### login into postgresql
```dtd
psql -U test -d test -h localhost -p 5432
```

```dtd
{"id":"123","first_name":"John","last_name":"Doe"}
```