package com.kasperovich.desoccer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication(scanBasePackages = "com.kasperovich.desoccer")
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableCaching
public class DeSoccerApplication {
    public static void main(String[] args) {
        SpringApplication.run(DeSoccerApplication.class, args);
    }
}
