package com.wilson.kafka_consumer;

import com.wilson.kafka_consumer.model.Person;
import com.wilson.kafka_consumer.model.converter.DataConverter;
import com.wilson.kafka_consumer.service.TestConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Service
public class TestTopicConsumer {

    @Autowired
    DataConverter dataConverter;

    @Autowired
    TestConsumerService testConsumerService;

    @KafkaListener(topics = "test-topic", groupId = "my-group")
    public void consumeMessages(String payload, @Header(KafkaHeaders.RECEIVED_PARTITION) int partition, @Header(KafkaHeaders.OFFSET) long offset)
    {
        System.out.printf("Received message: '%s' on partition %d at offset %d", payload, partition, offset);
        System.out.println("convert data");
        Person person = dataConverter.convertData(payload);
        testConsumerService.process(person);
        System.out.println("processed message");
    }

}
