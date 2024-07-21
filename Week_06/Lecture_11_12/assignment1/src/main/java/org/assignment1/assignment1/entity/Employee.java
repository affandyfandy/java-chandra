package org.assignment1.assignment1.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

import org.hibernate.annotations.BatchSize;

@Data
@Setter
@Getter
@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "emp_no")
    private int empNo;

    @Column(name = "birth_date", nullable = false)
    private Date birthDate;

    @Column(name = "first_name", nullable = false, length = 14)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 16)
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false, length = 1)
    private Gender gender;

    @Column(name = "hire_date", nullable = false)
    private Date hireDate;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "employees", cascade = CascadeType.ALL)
    @BatchSize(size = 10)
    private List<DeptEmploy> deptEmploys = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "employees", cascade = CascadeType.ALL)
    @BatchSize(size = 10)
    private List<DeptManager> deptManagers = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "employees", cascade = CascadeType.ALL)
    @BatchSize(size = 10)
    private List<Salary> salaries = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "employees", cascade = CascadeType.ALL)
    @BatchSize(size = 10)
    private List<Title> titles = new ArrayList<>();

}
