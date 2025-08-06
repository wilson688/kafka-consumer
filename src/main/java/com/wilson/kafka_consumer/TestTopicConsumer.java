package com.wilson.kafka_consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.wilson.kafka_consumer.model.Person;
import com.wilson.kafka_consumer.model.converter.DataConverter;
import com.wilson.kafka_consumer.service.TestConsumerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Service
public class TestTopicConsumer {

    private static final Logger log = LoggerFactory.getLogger(TestTopicConsumer.class);

    @Autowired
    DataConverter dataConverter;

    @Autowired
    TestConsumerService testConsumerService;

    @KafkaListener(topics = "test-topic", groupId = "my-group")
    public void consumeMessages(String payload, @Header(KafkaHeaders.RECEIVED_PARTITION) int partition, @Header(KafkaHeaders.OFFSET) long offset) throws JsonProcessingException {
        log.info("Received message: {} on partition {} at offset {}", payload, partition, offset);
        log.info("convert data");
        Person person = dataConverter.convertData(payload);
        testConsumerService.process(person);
        log.info("processed message");
    }

}
