package com.assignment3.assignment3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.assignment3.assignment3.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
}