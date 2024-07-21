package org.assignment1.assignment3.repository;

import org.assignment1.assignment3.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PrimaryEmployeeRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    @Qualifier("primaryJdbcTemplate")
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Employee> getAllEmployees() {
        String sql = "select * from employee";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Employee.class));
    }

    @SuppressWarnings("deprecation")
    public Employee getEmployeeById(int id) {
        String sql = "select * from employee where id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[] { id }, new BeanPropertyRowMapper<>(Employee.class));
    }

    public int addEmployee(Employee employee) {
        String sql = "INSERT INTO employee (name, age, email) VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql, employee.getName(), employee.getAge(), employee.getEmail());
    }

    public int updateEmployee(Employee employee) {
        String sql = "UPDATE employee SET name = ?, age = ?, email = ? WHERE id = ?";
        return jdbcTemplate.update(sql, employee.getName(), employee.getAge(), employee.getEmail(), employee.getId());
    }

    public int deleteById(int id) {
        String sql = "DELETE FROM Employee WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

}
