package org.assignment1.assignment1.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.io.Serializable;

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