package org.assignment1.assignment3;

import org.assignment1.assignment3.model.Employee;
import org.assignment1.assignment3.repository.PrimaryEmployeeRepository;
import org.assignment1.assignment3.repository.SecondaryEmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.annotation.Rollback;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.transaction.annotation.Transactional;
import org.assignment1.assignment3.service.EmployeeService;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @InjectMocks
    private EmployeeService employeeService;

    @Mock
    private PrimaryEmployeeRepository primaryEmployeeRepo;

    @Mock
    private SecondaryEmployeeRepository secondaryEmployeeRepo;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllEmployeesFromPrimary() {
        Employee emp1 = new Employee(1, "John Doe", 50, "john.doe@example.com");
        Employee emp2 = new Employee(2, "Jane Doe", 50, "jane.doe@example.com");

        when(primaryEmployeeRepo.getAllEmployees()).thenReturn(Arrays.asList(emp1, emp2));

        List<Employee> employees = employeeService.getAllEmployeesFromPrimary();

        assertNotNull(employees);
        assertEquals(2, employees.size());
        assertEquals("John Doe", employees.get(0).getName());
        assertEquals("Jane Doe", employees.get(1).getName());
    }

    @Test
    public void testGetEmployeeByIdFromPrimary() {
        Employee emp = new Employee(1, "John Doe", 50, "john.doe@example.com");

        when(primaryEmployeeRepo.getEmployeeById(1)).thenReturn(emp);

        Employee result = employeeService.getEmployeeByIdFromPrimary(1);

        assertNotNull(result);
        assertEquals("John Doe", result.getName());
    }

    @Test
    @Transactional("primaryTransactionManager")
    @Rollback(true)
    public void testAddEmployeeToPrimary() {
        Employee emp = new Employee(1, "John Doe", 50, "john.doe@example.com");

        when(primaryEmployeeRepo.addEmployee(emp)).thenReturn(1);

        int result = employeeService.addEmployeeToPrimary(emp);

        assertEquals(1, result);
    }

    @Test
    @Transactional("primaryTransactionManager")
    @Rollback(true)
    public void testUpdateEmployeeFromPrimary() {
        Employee emp = new Employee(1, "John Doe", 50, "john.doe@example.com");

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
        Employee emp1 = new Employee(1, "Alice Smith", 50, "alice.smith@example.com");
        Employee emp2 = new Employee(2, "Bob Brown", 50, "bob.brown@example.com");

        when(secondaryEmployeeRepo.getAllEmployees()).thenReturn(Arrays.asList(emp1, emp2));

        List<Employee> employees = employeeService.getAllEmployeesFromSecondary();

        assertNotNull(employees);
        assertEquals(2, employees.size());
        assertEquals("Alice Smith", employees.get(0).getName());
        assertEquals("Bob Brown", employees.get(1).getName());
    }

    @Test
    public void testGetEmployeeByIdFromSecondary() {
        Employee emp = new Employee(1, "Alice Smith", 50, "alice.smith@example.com");

        when(secondaryEmployeeRepo.getEmployeeById(1)).thenReturn(emp);

        Employee result = employeeService.getEmployeeByIdFromSecondary(1);

        assertNotNull(result);
        assertEquals("Alice Smith", result.getName());
    }

    @Test
    @Transactional("secondaryTransactionManager")
    @Rollback(true)
    public void testAddEmployeeToSecondary() {
        Employee emp = new Employee(1, "Alice Smith", 50, "alice.smith@example.com");

        when(secondaryEmployeeRepo.addEmployee(emp)).thenReturn(1);

        int result = employeeService.addEmployeeToSecondary(emp);

        assertEquals(1, result);
    }

    @Test
    @Transactional("secondaryTransactionManager")
    @Rollback(true)
    public void testUpdateEmployeeInSecondary() {
        Employee emp = new Employee(1, "Alice Smith", 50, "alice.smith@example.com");

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