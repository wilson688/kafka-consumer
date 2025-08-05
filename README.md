# kafka-consumer

## RUN LOCALLY BY USING DOCKER-COMPOSE
```dtd
 docker-compose -f docker-compose.yml up
```

### POST MESSAGES VIA PRODUCER
```dtd
docker-compose exec kafka bash
kafka-console-producer --broker-list localhost:9092 --topic test-topic
```

#### POST THIS MESSAGE: 
```dtd
{"id”:323, “first_name”:”w”, “last_name”:”s”}
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
