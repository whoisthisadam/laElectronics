package com.kasperovich;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(scanBasePackages = "com.kasperovich")
@EnableSwagger2
public class DeSoccerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DeSoccerApplication.class, args);
    }
}
