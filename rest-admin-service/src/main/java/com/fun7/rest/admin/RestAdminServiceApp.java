package com.fun7.rest.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={"com.fun7"})
public class RestAdminServiceApp {
    public static void main(String[] args) {
        SpringApplication.run(RestAdminServiceApp.class, args);
    }
}
