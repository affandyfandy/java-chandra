package com.assignment1.assignment1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.assignment1.assignment1.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
}