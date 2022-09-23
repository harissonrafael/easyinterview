package com.bridge.easyinterview;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ActiveProfiles("test")
@SpringBootTest
class EasyInterviewApplicationTests {

	@Autowired
	private ConfigurableApplicationContext context;

	@Test
	void test() {
		assertTrue(context.isActive());
	}

}
