package com.wilson.kafka_consumer.service.impl;

import com.wilson.kafka_consumer.model.Person;
import com.wilson.kafka_consumer.service.TestConsumerService;
import org.springframework.stereotype.Service;

@Service
public class TestConsumerServiceImpl implements TestConsumerService {

    @Override
    public void saveInformation(Person person) {

    }

    @Override
    public Person getById(int id) {
        return null;
    }

    @Override
    public void process(Person person) {
        Person personRecord = getById(person.getId());
        if (personRecord == null)
        {
            System.out.println("no person found creating creating new person");
            saveInformation(personRecord);
        }
        else
        {
            System.out.println("person record already exits");
        }
    }
}
