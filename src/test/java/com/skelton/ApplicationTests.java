package com.skelton;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles({"localhost, test"})
public class ApplicationTests {

    @Test
    public void contextLoads() {
    }
}