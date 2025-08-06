package com.wilson.kafka_consumer.repository;

import com.wilson.kafka_consumer.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Jdbcrepository extends JpaRepository<Person, String> {

    Person save(Person person);

    Person getById(int id);
}
