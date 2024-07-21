package org.assignment1.assignment1.repository;

import org.assignment1.assignment1.entity.DeptEmploy;
import org.assignment1.assignment1.entity.DeptEmployId;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeptEmployRepository extends JpaRepository<DeptEmploy, DeptEmployId> {
    List<DeptEmploy> findByIdEmpNo(Integer empNo);

    List<DeptEmploy> findById_EmpNo(Integer empNo);
}
