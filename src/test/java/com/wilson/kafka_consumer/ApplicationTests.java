package com.wilson.kafka_consumer;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.junit.jupiter.api.Test;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@SpringBootTest
@Import(TestcontainersConfiguration.class)
class ApplicationTests {

	@Test
	void contextLoads() {
		// if this runs without exception, your context is healthy
	}
}
