package com.assignment3.assignment3.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.assignment3.assignment3.entity.Student;
import com.assignment3.assignment3.service.StudentService;

@RestController
@RequestMapping("/students")
public class StudentController {

        private final StudentService studentService;

        @Autowired
        public StudentController(StudentService studentService) {
                this.studentService = studentService;
        }

        @PostMapping("/create")
        public ResponseEntity<Student> createStudent(@RequestBody Student student,
                        @RequestHeader("username") String username) {
                System.out.println("Username: " + username);
                Student createdStudent = studentService.createStudent(student);
                return ResponseEntity.ok().header("source", "fpt-software")
                                .header("timestamp", java.time.LocalDateTime.now().toString()).body(createdStudent);
        }

        @GetMapping("/get-all")
        public ResponseEntity<List<Student>> getAllStudents(@RequestHeader("username") String username) {
                System.out.println("Username: " + username);
                List<Student> students = studentService.getAllStudents();
                return ResponseEntity.ok().header("source", "fpt-software")
                                .header("timestamp", java.time.LocalDateTime.now().toString()).body(students);
        }

        @GetMapping("/{id}")
        public ResponseEntity<Student> getStudentById(@PathVariable Long id,
                        @RequestHeader("username") String username) {
                System.out.println("Username: " + username);
                Student student = studentService.getStudentById(id);
                return student != null
                                ? ResponseEntity.ok().header("source", "fpt-software")
                                                .header("timestamp", java.time.LocalDateTime.now().toString())
                                                .body(student)
                                : ResponseEntity.notFound().build();
        }

        @PutMapping("/{id}")
        public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student student,
                        @RequestHeader("username") String username) {
                System.out.println("Username: " + username);
                Student updatedStudent = studentService.updateStudent(id, student);
                return updatedStudent != null
                                ? ResponseEntity.ok().header("source", "fpt-software")
                                                .header("timestamp", java.time.LocalDateTime.now().toString())
                                                .body(updatedStudent)
                                : ResponseEntity.notFound().build();
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<String> deleteStudent(@PathVariable Long id, @RequestHeader("username") String username) {
                System.out.println("Username: " + username);
                studentService.deleteStudent(id);
                return ResponseEntity.ok().header("source", "fpt-software")
                                .header("timestamp", java.time.LocalDateTime.now().toString())
                                .body("Student successfully deleted.");
        }
}