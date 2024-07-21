package org.assignment1.assignment1.repository;

import java.util.List;
import java.util.UUID;
import org.assignment1.assignment1.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, UUID> {
    List<Employee> findByDepartment(String department);
}
