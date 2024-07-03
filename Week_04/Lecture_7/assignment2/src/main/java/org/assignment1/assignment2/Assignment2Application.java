package org.assignment1.assignment2;

import org.assignment1.assignment2.Services.EmailService;
import org.assignment1.assignment2.Services.EmployeeService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Assignment2Application {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Assignment2Application.class, args);

        EmployeeService employeeService1 = (EmployeeService) context.getBean("employeeService");
        employeeService1.setEmail("ryanfpt@gmail.com");
        EmailService emailService1 = (EmailService) context.getBean("emailService");
        emailService1.sendEmail(employeeService1.getEmail(), "Subject Test", "Body Test");
    }
}
