package ru.alfabank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class TestTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestTaskApplication.class, args);
    }

}
