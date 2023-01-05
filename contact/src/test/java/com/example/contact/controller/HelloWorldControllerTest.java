package com.example.contact.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class HelloWorldControllerTest {
    @Autowired
    private HelloWorldController HelloWorldController;

    @Test
    public void helloWorld() {
        assertThat(HelloWorldController.helloWorld()).isEqualTo("HelloWorld!");
    }
}