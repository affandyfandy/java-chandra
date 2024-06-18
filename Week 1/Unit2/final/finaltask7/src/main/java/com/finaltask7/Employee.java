package com.finaltask7;

import java.time.LocalDate;

public class Employee {
    private String id;
    private String name;
    private LocalDate dateOfBirth;
    private String address;
    private String department;

    Employee(String id, String name, LocalDate dateOfbirth, String address, String department) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfbirth;
        this.address = address;
        this.department = department;
    }

    public String getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDateBirth() {
        return dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public String getDepartment() {
        return department;
    }
}
