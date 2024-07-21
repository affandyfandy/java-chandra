package org.assignment1.assignment2.repository;

import org.assignment1.assignment2.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.DataAccessException;

import java.util.List;

@Repository
public class EmployeeRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Employee> getAllEmployees() {
        String sql = "select * from employee";
        try {
            return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Employee.class));
        } catch (DataAccessException e) {
            // Handle the exception, log it, and return an appropriate response
            e.printStackTrace();
            return null; // or return an empty list, or throw a custom exception
        }
    }

    public Employee getEmployeeById(int id) {
        String sql = "select * from employee where id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[] { id }, new BeanPropertyRowMapper<>(Employee.class));
        } catch (EmptyResultDataAccessException e) {
            // Handle the case where no employee is found
            e.printStackTrace();
            return null; // or return an Optional<Employee> and return Optional.empty()
        } catch (DataAccessException e) {
            // Handle other potential DataAccessExceptions
            e.printStackTrace();
            return null;
        }
    }

    public int addEmployee(Employee employee) {
        String sql = "INSERT INTO employee (name, age, email) VALUES (?, ?, ?)";
        try {
            return jdbcTemplate.update(sql, employee.getName(), employee.getAge(), employee.getEmail());
        } catch (DataAccessException e) {
            // Handle the exception, log it, and return an appropriate response
            e.printStackTrace();
            return 0; // or throw a custom exception
        }
    }

    public int updateEmployee(Employee employee) {
        String sql = "UPDATE employee SET name = ?, age = ?, email = ? WHERE id = ?";
        try {
            return jdbcTemplate.update(sql, employee.getName(), employee.getAge(), employee.getEmail(),
                    employee.getId());
        } catch (DataAccessException e) {
            // Handle the exception, log it, and return an appropriate response
            e.printStackTrace();
            return 0; // or throw a custom exception
        }
    }

    public int deleteById(int id) {
        String sql = "DELETE FROM Employee WHERE id = ?";
        try {
            return jdbcTemplate.update(sql, id);
        } catch (DataAccessException e) {
            // Handle the exception, log it, and return an appropriate response
            e.printStackTrace();
            return 0; // or throw a custom exception
        }
    }
}