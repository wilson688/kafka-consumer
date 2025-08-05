package com.wilson.kafka_consumer.service;


import com.wilson.kafka_consumer.model.Person;

public interface TestConsumerService {

    void saveInformation(Person person);

    Person getById(int id);

    void process(Person person);
}
