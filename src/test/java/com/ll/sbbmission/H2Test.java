package com.ll.sbbmission;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestPropertySource(properties = {"spring.datasource.url=jdbc:h2:mem:testdb"})
public class H2Test {

    @Value("${spring.datasource.url}")
    private String databaseUrl;

    @Test
    public void testDatabaseUrl() {
        assertEquals("jdbc:h2:mem:testdb", databaseUrl);
    }
}

