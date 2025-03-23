package com.mlinyun.springboot3demo.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class HelloServiceTest {

    @Autowired
    private HelloService helloService;

    @Test
    void testSayHello() {
        String result = helloService.sayHello("LingYun");
        assertNotNull(result);
        assertEquals("Hello, LingYun!", result);
    }

    @Test
    void testSayHello2() {
        String result = helloService.sayHello("Spring Boot");
        assertNotNull(result);
        assertEquals("Hello, Spring Boot!", result);
    }

}