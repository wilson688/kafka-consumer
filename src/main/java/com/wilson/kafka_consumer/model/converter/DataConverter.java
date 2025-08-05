package com.wilson.kafka_consumer.model.converter;


import com.wilson.kafka_consumer.model.Person;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class DataConverter {

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
        String[] keyValuePairs = payload.split(",");
        Map<String, String> resultMap = new HashMap<>();

        for (String pair : keyValuePairs)
        {
            String[] entry = pair.split("=");
            if (entry.length == 2)
            {
                resultMap.put(entry[0].trim(), entry[1].trim());
            }
            else
            {
                System.out.println("Malformed key-value pair: " + pair);
            }
        }
        return resultMap;
    }
}
