package org.assignment1.assignment2.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Setter
@Getter
@Data
public class employee {

    @Id
    private int id;
    private String name;
    private int age;
    private String email;

}
