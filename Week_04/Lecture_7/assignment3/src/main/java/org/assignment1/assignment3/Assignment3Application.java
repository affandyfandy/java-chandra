package org.assignment1.assignment3;

import org.assignment1.assignment3.services.EmailService;
import org.assignment1.assignment3.services.EmployeeService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Assignment3Application {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Assignment3Application.class, args);

        EmployeeService employeeService1 = (EmployeeService) context.getBean("employeeService");
        EmployeeService employeeService2 = (EmployeeService) context.getBean("employeeService");

        employeeService1.setEmail("ryanfpt@gmail.com");
        employeeService2.setEmail("chandrafpt@gmail.com");

        EmailService emailService1 = (EmailService) context.getBean("emailService");
        emailService1.sendEmail(employeeService1.getEmail(), employeeService2.getEmail(), "Test Subject 1", "Test Body 1");
        emailService1.sendEmail(employeeService2.getEmail(), employeeService1.getEmail(), "Test Subject 2", "Test Body 2");

        // Observing scopes
        System.out.println("EmployeeService 1: " + employeeService1);
        System.out.println("EmployeeService 2: " + employeeService2);
        System.out.println("EmailService 1: " + emailService1);
    }

}
