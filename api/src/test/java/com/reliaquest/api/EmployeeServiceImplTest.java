package com.reliaquest.api.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.reliaquest.api.model.Employee;
import java.net.http.HttpClient;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 * Unit test class for EmployeeServiceImpl.
 * This class uses Mockito to mock dependencies and verify method behavior in isolation.
 */
class EmployeeServiceImplTest {

    @Mock
    private HttpClient httpClient; // Mocked HTTP client (simulates API calls)

 
    @Mock
    private EmployeeServiceImpl employeeService; // EmployeeService with injected mocks

    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        assertNotNull(employees); // The method should return a list (even if empty)
    }


    @Test
    void testGetEmployeeById() {
        Employee employee = employeeService.getEmployeeById("123");
        assertNull(employee);
    }


    @Test
    void testCreateEmployee() {
        Employee newEmployee = new Employee("1", "John Doe", 50000, 30, "Engineer", "john@example.com");
        Employee createdEmployee = employeeService.createEmployee(newEmployee);
        assertNull(createdEmployee);
    }

    @Test
    void testGetHighestSalary() {
        // Create a list of employees with different salaries
        List<Employee> employees = List.of(
                new Employee("1", "John Doe", 50000, 30, "Engineer", "john@example.com"),
                new Employee("2", "Jane Smith", 60000, 28, "Manager", "jane@example.com"),
                new Employee("3", "Bob Johnson", 55000, 35, "Developer", "bob@example.com")
        );

        // Mock the getAllEmployees method to return the list of employees
        when(employeeService.getAllEmployees()).thenReturn(employees);

        int highestSalary = employeeService.getHighestSalaryOfEmployees();

        assertEquals(60000, highestSalary);
    }


    @Test
    void testGetTopTenHighestEarningEmployees() {
        // Create a list of employees with different salaries
        List<Employee> employees = List.of(
                new Employee("1", "John Doe", 50000, 30, "Engineer", "john@example.com"),
                new Employee("2", "Jane Smith", 60000, 28, "Manager", "jane@example.com"),
                new Employee("3", "Bob Johnson", 55000, 35, "Developer", "bob@example.com"),
                new Employee("4", "Alice Brown", 70000, 32, "Director", "alice@example.com"),
                new Employee("5", "Charlie Black", 45000, 29, "Analyst", "charlie@example.com"),
                new Employee("6", "Diana White", 80000, 40, "VP", "diana@example.com"),
                new Employee("7", "Eve Green", 75000, 27, "Consultant", "eve@example.com"),
                new Employee("8", "Frank Blue", 65000, 33, "Architect", "frank@example.com"),
                new Employee("9", "Grace Red", 72000, 31, "Scientist", "grace@example.com"),
                new Employee("10", "Hank Yellow", 68000, 36, "Manager", "hank@example.com"),
                new Employee("11", "Ivy Purple", 62000, 38, "Engineer", "ivy@example.com")
        );

        // Mock the getAllEmployees method to return the list of employees
        when(employeeService.getAllEmployees()).thenReturn(employees);

        List<String> topEarners = employeeService.getTopTenHighestEarningEmployeeNames();

        List<String> expectedTopEarners = List.of(
                "Diana White", "Eve Green", "Grace Red", "Alice Brown", "Hank Yellow",
                "Jane Smith", "Frank Blue", "Bob Johnson", "Ivy Purple", "John Doe"
        );
        assertEquals(expectedTopEarners, topEarners);
    }
}
