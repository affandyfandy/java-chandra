package org.assignment1.assignment1.dto;

import java.util.Date;
import lombok.Data;

@Data
public class SearchEmployeeDynamicDTO {
    private Integer empNo;
    private String firstName;
    private String lastName;
    private String gender;
    private Date hireDate;
    private Date birthDate;
}
