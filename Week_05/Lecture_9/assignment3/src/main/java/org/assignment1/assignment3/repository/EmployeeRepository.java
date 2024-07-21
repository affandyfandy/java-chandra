package org.assignment1.assignment3.repository;

import org.assignment1.assignment3.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, String> {
    List<Employee> findAllByOrderByLastNameAsc();
}
