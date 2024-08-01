package com.assignment2.assignment2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.assignment2.assignment2.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
