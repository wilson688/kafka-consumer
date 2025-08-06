package com.wilson.kafka_consumer.service.impl;

import com.wilson.kafka_consumer.model.Person;
import com.wilson.kafka_consumer.repository.Jdbcrepository;
import com.wilson.kafka_consumer.service.TestConsumerService;
import org.springframework.stereotype.Service;

@Service
public class TestConsumerServiceImpl implements TestConsumerService {


    private final Jdbcrepository jdbcrepository;

    public TestConsumerServiceImpl(Jdbcrepository jdbcrepository) {
        this.jdbcrepository = jdbcrepository;
    }

    @Override
    public void saveInformation(Person person) {
        jdbcrepository.save(person);
    }

    @Override
    public Person getById(int id) {
        return jdbcrepository.getById(id);
    }

    @Override
    public void process(Person person) {
        Person personRecord = getById(person.getId());
        if (personRecord != null)
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
