package org.assignment1.assignment3.repository;

import org.assignment1.assignment3.model.employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class secondaryEmployeeRepository {

    @Autowired
    @Qualifier("secondaryJdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    public List<employee> getAllEmployees() {
        String sql = "SELECT * FROM employee";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new employee(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getInt("age"),
                rs.getString("email")));
    }

    @SuppressWarnings("deprecation")
    public employee getEmployeeById(int id) {
        String sql = "SELECT * FROM employee WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[] { id }, (rs, rowNum) -> new employee(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getInt("age"),
                rs.getString("email")));
    }

    public int addEmployee(employee employee) {
        String sql = "INSERT INTO employee (name, age, email) VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql, employee.getName(), employee.getAge(), employee.getEmail());
    }

    public int updateEmployee(employee employee) {
        String sql = "UPDATE employee SET name = ?, age = ?, email = ? WHERE id = ?";
        return jdbcTemplate.update(sql, employee.getName(), employee.getAge(), employee.getEmail(), employee.getId());
    }

    public int deleteById(int id) {
        String sql = "DELETE FROM employee WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

}
