package org.assignment1.assignment3;

import org.assignment1.assignment3.model.employee;
import org.assignment1.assignment3.repository.primaryEmployeeRepository;
import org.assignment1.assignment3.repository.secondaryEmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.annotation.Rollback;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.transaction.annotation.Transactional;
import org.assignment1.assignment3.service.employeeService;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @InjectMocks
    private employeeService employeeService;

    @Mock
    private primaryEmployeeRepository primaryEmployeeRepo;

    @Mock
    private secondaryEmployeeRepository secondaryEmployeeRepo;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllEmployeesFromPrimary() {
        employee emp1 = new employee(1, "John Doe", 50, "john.doe@example.com");
        employee emp2 = new employee(2, "Jane Doe", 50, "jane.doe@example.com");

        when(primaryEmployeeRepo.getAllEmployees()).thenReturn(Arrays.asList(emp1, emp2));

        List<employee> employees = employeeService.getAllEmployeesFromPrimary();

        assertNotNull(employees);
        assertEquals(2, employees.size());
        assertEquals("John Doe", employees.get(0).getName());
        assertEquals("Jane Doe", employees.get(1).getName());
    }

    @Test
    public void testGetEmployeeByIdFromPrimary() {
        employee emp = new employee(1, "John Doe", 50, "john.doe@example.com");

        when(primaryEmployeeRepo.getEmployeeById(1)).thenReturn(emp);

        employee result = employeeService.getEmployeeByIdFromPrimary(1);

        assertNotNull(result);
        assertEquals("John Doe", result.getName());
    }

    @Test
    @Transactional("primaryTransactionManager")
    @Rollback(true)
    public void testAddEmployeeToPrimary() {
        employee emp = new employee(1, "John Doe", 50, "john.doe@example.com");

        when(primaryEmployeeRepo.addEmployee(emp)).thenReturn(1);

        int result = employeeService.addEmployeeToPrimary(emp);

        assertEquals(1, result);
    }

    @Test
    @Transactional("primaryTransactionManager")
    @Rollback(true)
    public void testUpdateEmployeeFromPrimary() {
        employee emp = new employee(1, "John Doe", 50, "john.doe@example.com");

        when(primaryEmployeeRepo.updateEmployee(emp)).thenReturn(1);

        int result = employeeService.updateEmployeeFromPrimary(emp);

        assertEquals(1, result);
    }

    @Test
    @Transactional("primaryTransactionManager")
    @Rollback(true)
    public void testDeleteByIdFromPrimary() {
        when(primaryEmployeeRepo.deleteById(1)).thenReturn(1);

        int result = employeeService.deleteByIdFromPrimary(1);

        assertEquals(1, result);
    }

    @Test
    public void testGetAllEmployeesFromSecondary() {
        employee emp1 = new employee(1, "Alice Smith", 50, "alice.smith@example.com");
        employee emp2 = new employee(2, "Bob Brown", 50, "bob.brown@example.com");

        when(secondaryEmployeeRepo.getAllEmployees()).thenReturn(Arrays.asList(emp1, emp2));

        List<employee> employees = employeeService.getAllEmployeesFromSecondary();

        assertNotNull(employees);
        assertEquals(2, employees.size());
        assertEquals("Alice Smith", employees.get(0).getName());
        assertEquals("Bob Brown", employees.get(1).getName());
    }

    @Test
    public void testGetEmployeeByIdFromSecondary() {
        employee emp = new employee(1, "Alice Smith", 50, "alice.smith@example.com");

        when(secondaryEmployeeRepo.getEmployeeById(1)).thenReturn(emp);

        employee result = employeeService.getEmployeeByIdFromSecondary(1);

        assertNotNull(result);
        assertEquals("Alice Smith", result.getName());
    }

    @Test
    @Transactional("secondaryTransactionManager")
    @Rollback(true)
    public void testAddEmployeeToSecondary() {
        employee emp = new employee(1, "Alice Smith", 50, "alice.smith@example.com");

        when(secondaryEmployeeRepo.addEmployee(emp)).thenReturn(1);

        int result = employeeService.addEmployeeToSecondary(emp);

        assertEquals(1, result);
    }

    @Test
    @Transactional("secondaryTransactionManager")
    @Rollback(true)
    public void testUpdateEmployeeInSecondary() {
        employee emp = new employee(1, "Alice Smith", 50, "alice.smith@example.com");

        when(secondaryEmployeeRepo.updateEmployee(emp)).thenReturn(1);

        int result = employeeService.updateEmployeeInSecondary(emp);

        assertEquals(1, result);
    }

    @Test
    @Transactional("secondaryTransactionManager")
    @Rollback(true)
    public void testDeleteByIdFromSecondary() {
        when(secondaryEmployeeRepo.deleteById(1)).thenReturn(1);

        int result = employeeService.deleteByIdFromSecondary(1);

        assertEquals(1, result);
    }
}