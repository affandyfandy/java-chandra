package org.assignment1.assignment1.dto;

import java.util.List;
import java.util.Date;
import lombok.Data;

@Data
public class EmployeeDTO {

    private Integer empNo;
    private Date birthDate;
    private String firstName;
    private String lastName;
    private String gender;
    private Date hireDate;

    private List<SalaryDTO> salaries;
    private List<TitleDTO> titles;
    private List<DeptEmploysDTO> deptEmploys;
    private List<DeptManagerDTO> deptManagers;
}
