package org.assignment1.assignment1.dto;

import java.util.Date;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class SalaryDTO {

    private Integer empNo;
    private Integer salary;
    private Date fromDate;
    private Date toDate;
}