package org.assignment1.assignment1.entity;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;

@Getter
@Setter
public class DeptManagerId implements Serializable {
    @Column(name = "dept_no")
    private String deptNo;

    @Column(name = "emp_no")
    private Integer empNo;

}
