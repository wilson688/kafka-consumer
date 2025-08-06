package com.wilson.kafka_consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wilson.kafka_consumer.model.Person;
import com.wilson.kafka_consumer.model.converter.DataConverter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class DataConverterTest {

    @InjectMocks
    DataConverter dataConverter;

    @Mock
    ObjectMapper objectMapper;

    @Test
    void test() throws JsonProcessingException {
        String test = "{\"id\":\"123\",\"first_name\":\"John\",\"last_name\":\"Doe\"}";
        Person person = dataConverter.convertData(test);
        assertEquals(123, person.getId());
        assertEquals("John",   person.getFirst_name());
        assertEquals("Doe", person.getLast_name());

    }
}
