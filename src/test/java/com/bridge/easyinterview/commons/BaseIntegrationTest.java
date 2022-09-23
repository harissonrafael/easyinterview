package com.bridge.easyinterview.commons;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@TestPropertySource("classpath:application-test.yml")
public abstract class BaseIntegrationTest {
}
