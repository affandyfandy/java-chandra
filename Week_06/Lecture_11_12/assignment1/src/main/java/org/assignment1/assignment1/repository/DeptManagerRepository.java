package org.assignment1.assignment1.repository;

import org.assignment1.assignment1.entity.DeptManager;
import org.assignment1.assignment1.entity.DeptManagerId;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DeptManagerRepository extends JpaRepository<DeptManager, DeptManagerId> {
    List<DeptManager> findByIdEmpNo(Integer empNo);

    List<DeptManager> findById_EmpNo(Integer empNo);
}