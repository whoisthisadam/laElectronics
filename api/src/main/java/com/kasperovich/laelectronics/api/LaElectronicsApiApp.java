package com.kasperovich.laelectronics.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication(scanBasePackages = "com.kasperovich.laelectronics.api")
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableCaching
public class LaElectronicsApiApp {
    public static void main(String[] args) {
        SpringApplication.run(LaElectronicsApiApp.class, args);
    }
}
