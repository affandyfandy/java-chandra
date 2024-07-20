package org.assignment1.assignment1.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@Entity
@Table(name = "dept_manager")
public class DeptManager {

    @EmbeddedId
    private DeptManagerId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("empNo")
    @JoinColumn(name = "emp_no")
    @JsonIgnore
    private Employee employees;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("deptNo")
    @JoinColumn(name = "dept_no")
    @JsonIgnore
    private Department departments;

    @Column(name = "from_date", nullable = false)
    private Date fromDate;

    @Column(name = "to_date", nullable = false)
    private Date toDate;
}
