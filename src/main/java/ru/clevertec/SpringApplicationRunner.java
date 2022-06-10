package ru.clevertec;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.clevertec.service.OrderSequenceService;

@SpringBootApplication
public class SpringApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringApplicationRunner.class, args);
    }
}
