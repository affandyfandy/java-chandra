package org.assignment1.assignment3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class Assignment3Application {

    public static void main(String[] args) {
        SpringApplication.run(Assignment3Application.class, args);
    }

}
