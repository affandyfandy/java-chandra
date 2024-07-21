package org.assignment1.assignment3.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Setter
@Getter
@Data
public class Employee {

    @Id
    private int id;
    private String name;
    private int age;
    private String email;

    public Employee(int id, String name, int age, String email) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
    }

    public Employee() {
    }
}
