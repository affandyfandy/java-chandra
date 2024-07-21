package org.assignment1.assignment1.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "departments")
public class Department {

    @Id
    @Column(name = "dept_no", length = 4)
    private String deptNo;

    @Column(name = "dept_name", nullable = false, unique = true, length = 40)
    private String deptName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "departments", cascade = CascadeType.ALL)
    private List<DeptEmploy> deptEmploys = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "departments", cascade = CascadeType.ALL)
    private List<DeptManager> deptManager = new ArrayList<>();

}
