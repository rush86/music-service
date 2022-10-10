package com.musicservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles({"localhost, test"})
public class ApplicationTests {

    @Test
    void contextLoads() {
    }
}