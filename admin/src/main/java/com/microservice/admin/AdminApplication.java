package com.microservice.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
 * First of all this is a small sample, but perfectly written.
 * This admin service is created for the admin panel.
 * Spring Boot, Spring Security, JPA, JDBC, Maven, PostgreSQL, Lombok are used here.
 */

@SpringBootApplication
public class AdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class,args);
    }
}
