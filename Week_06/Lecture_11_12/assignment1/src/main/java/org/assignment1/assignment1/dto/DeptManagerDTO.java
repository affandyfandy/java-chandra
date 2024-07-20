package org.assignment1.assignment1.dto;

import java.util.Date;
import lombok.Data;

@Data
public class DeptManagerDTO {
    private Integer empNo;
    private String deptNo;
    private Date fromDate;
    private Date toDate;
}
