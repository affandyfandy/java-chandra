package org.assignment1.assignment1.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;

@Data
@Embeddable
public class SalaryId implements Serializable {

    @Column(name = "emp_no")
    private Integer empNo;

    @Column(name = "from_date")
    private Date fromDate;

}
