package com.flower.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.flower.*")
public class BootApplication {

    public static void main(String args[]) {
        SpringApplication.run(BootApplication.class, args);
    }

}
