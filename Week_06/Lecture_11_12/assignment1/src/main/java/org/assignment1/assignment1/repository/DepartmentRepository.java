package org.assignment1.assignment1.repository;

import org.assignment1.assignment1.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, String> {
}
