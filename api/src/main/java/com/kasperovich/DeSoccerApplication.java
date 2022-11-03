package com.kasperovich;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.kasperovich")
public class DeSoccerApplication {
    public static void main(String[] args) {
        SpringApplication.run(DeSoccerApplication.class, args);
    }
}
