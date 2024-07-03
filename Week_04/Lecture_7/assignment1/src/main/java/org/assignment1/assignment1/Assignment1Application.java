package org.assignment1.assignment1;


import org.assignment1.assignment1.entity.Employee;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Assignment1Application {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(Assignment1Application.class, args);

		Employee employee  = context.getBean(Employee.class);
		employee.working();
	}

}
