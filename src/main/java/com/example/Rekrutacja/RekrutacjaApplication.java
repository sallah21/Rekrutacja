package com.example.Rekrutacja;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class RekrutacjaApplication {
    public static void main(String[] args) {
        SpringApplication.run(RekrutacjaApplication.class, args);
    }

}