package com.fun7.rest.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.fun7"})
public class RestUserServiceApp {

    public static void main(String[] args) {
        SpringApplication.run(RestUserServiceApp.class, args);
    }
}
