package org.assignment1.assignment1.repository;

import org.assignment1.assignment1.entity.Salary;
import org.assignment1.assignment1.entity.SalaryId;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalaryRepository extends JpaRepository<Salary, SalaryId> {
    List<Salary> findByIdEmpNo(Integer empNo);

    List<Salary> findById_EmpNo(Integer empNo);
}
