package com.github.bcanvural.basicregistration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@ImportResource({ "classpath:security.xml" })
public class BasicRegistrationApplication {

    public static void main(String[] args) {
        SpringApplication.run(BasicRegistrationApplication.class, args);
    }

}
