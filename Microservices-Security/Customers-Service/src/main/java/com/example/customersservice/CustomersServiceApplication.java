package com.example.customersservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CustomersServiceApplication {


    public static void main(String[] args) {
        SpringApplication.run(CustomersServiceApplication.class, args);
    }

}
