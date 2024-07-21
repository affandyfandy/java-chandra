package org.assignment1.assignment2.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "employee")
@Getter
@Setter
public class Employee implements Serializable {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate dob;
    private String address;
    private String department;
    private int salary;
}
