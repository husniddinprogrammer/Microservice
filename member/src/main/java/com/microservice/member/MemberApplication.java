package com.microservice.member;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
 * First of all this is a small sample, but perfectly written.
 * This member service is created for the member panel.
 * Spring Boot, JPA, JDBC, Maven, PostgreSQL, Lombok are used here.
 * Unlike admin service, Spring Security is not used.
 * This service allows you to connect to the admin panel.
 * It allows to add Member and User at the same time and we can have more possibilities.
 */

@SpringBootApplication
public class MemberApplication {
    public static void main(String[] args) {
        SpringApplication.run(MemberApplication.class,args);
    }
}
