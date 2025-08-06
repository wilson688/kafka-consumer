**KAFKA_BROKER_ID: 1**
A unique numeric identifier for this broker within the Kafka cluster. Every broker needs its own ID (e.g. 0, 1, 2, …).

**KAFKA_ZOOKEEPER_CONNECT: zookeeper:2181**
The host and port where this broker will register itself and fetch cluster metadata from ZooKeeper. (zookeeper is the Docker service name, port 2181 is ZooKeeper’s client port.)

**KAFKA_LISTENERS: PLAINTEXT_INTERNAL://0.0.0.0:29092,PLAINTEXT_EXTERNAL://0.0.0.0:9092**
Defines two network endpoints this broker will bind to:

PLAINTEXT_INTERNAL on port 29092 (for intra-cluster or Docker-internal traffic)

PLAINTEXT_EXTERNAL on port 9092 (for clients on the host or other networks)

**KAFKA_ADVERTISED_LISTENERS: PLAINTEXT_INTERNAL://kafka:29092,PLAINTEXT_EXTERNAL://host.docker.internal:9092**
What the broker tells clients to use when connecting:

Inside Docker (other services in the same Compose network) use hostname kafka:29092

Outside Docker (e.g. your host machine) use host.docker.internal:9092
(Adjust host.docker.internal to your host’s address if needed.)

**KAFKA_LISTENER_SECURITY_PROTOCOL_MAP: PLAINTEXT_INTERNAL:PLAINTEXT,PLAINTEXT_EXTERNAL:PLAINTEXT**
Maps each listener name to the security protocol it uses—in this case, both are unencrypted PLAINTEXT.

**KAFKA_INTER_BROKER_LISTENER_NAME: PLAINTEXT_INTERNAL**
Tells this broker which listener it should use when talking to other brokers in the cluster (here, the internal one on port 29092).

**KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR: 1**
The replication factor for the __consumer_offsets topic (which tracks consumer group offsets). With only one broker, you set this to 1; in a multi-broker setup you’d increase it for resilience.

**KAFKA_BOOTSTRAP_SERVERS: kafka:29092**
A convenience variable often used by clients or scripts; points to this broker’s internal listener so they can bootstrap their initial connection.

**Healthcheck**
This block tells Docker how to verify Kafka is up and running:

**test:**
Runs a shell command that:

Produces a message (health-check-message) to topic health-check-topic

Consumes one message from that same topic and greps for the string
If both succeed, the broker is healthy.

**interval: 30s**
Wait 30 seconds between healthchecks.

**timeout: 10s**
Consider the check failed if it doesn’t finish within 10 seconds.

**retries: 3**
Mark the container unhealthy after three consecutive failures.

**start_period: 20s**
Give Kafka 20 seconds after container start before beginning healthchecks (so it has time to initialize).