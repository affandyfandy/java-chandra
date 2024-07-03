package org.assignment1.assignment1.entity;

import org.assignment1.assignment1.config.EmployeeWork;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Employee {
    private int id = 1;
    private String name = "John Doe";
    private int age = 30;
    private EmployeeWork employeeWork;

    // Field Injection
//    @Autowired
//    private EmployeeWork employeeWork;

    // Constructor Injection
    // @Autowired
    // public Employee(EmployeeWork employeeWork) {
    // this.employeeWork = employeeWork;
    // }

//     Setter injection
     @Autowired
     public void setEmployeeWork(EmployeeWork employeeWork) {
        this.employeeWork = employeeWork;
     }

    public void working() {
        System.out.println("Employee Details: " + this.toString());
        employeeWork.work();
    }

    @Override
    public String toString() {
        return "Employee [id=" + id + ", name=" + name + ", age=" + age + "]";
    }
}
