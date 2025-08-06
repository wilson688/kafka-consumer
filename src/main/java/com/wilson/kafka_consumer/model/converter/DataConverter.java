package com.wilson.kafka_consumer.model.converter;


import com.wilson.kafka_consumer.TestTopicConsumer;
import com.wilson.kafka_consumer.model.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.util.HashMap;
import java.util.Map;

@Component
public class DataConverter {

    private static final Logger log = LoggerFactory.getLogger(TestTopicConsumer.class);

    private final ObjectMapper objectMapper; // Jackson ObjectMapper for JSON processing

    public DataConverter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public Person convertData(String payload)
    {
        Map<String, String> data = convertToMap(payload);
        Person person = new Person();
        person.setId(Integer.parseInt(data.get("id")));
        person.setFirst_name(data.get("first_name"));
        person.setLast_name(data.get("last_name"));
        return person;
    }


    public Map<String, String> convertToMap(String payload)
    {
        String cleaned = payload
                .substring(1, payload.length() - 1)      // removes the outer { }
                .replaceAll("\\\\\"", "\"");         // un‐escapes inner quotes

        String[] keyValuePairs = cleaned.split(",");
        Map<String, String> resultMap = new HashMap<>();

        for (String pair : keyValuePairs)
        {
            String[] entry = pair.split(":");
            if (entry.length == 2)
            {
                String key = entry[0].trim().replaceAll("^\"|\"$", "");
                String val = entry[1].trim().replaceAll("^\"|\"$", "");
                resultMap.put(key, val);
            }
            else
            {
                log.info("Malformed key-value pair: " + pair);
            }
        }
        return resultMap;
    }
}
