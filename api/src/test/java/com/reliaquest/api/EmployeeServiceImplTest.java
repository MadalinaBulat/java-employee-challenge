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

class EmployeeServiceImplTest {

    @Mock
    private HttpClient httpClient; // Mock HTTP client

    @InjectMocks
    private EmployeeServiceImpl employeeService; // Inject mock into service

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mocks
    }

    @Test
    void testGetAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        assertNotNull(employees); // Should return a list (even if empty)
    }

    @Test
    void testGetEmployeeById() {
        Employee employee = employeeService.getEmployeeById("123");
        assertNull(employee); // Should return null if employee not found
    }

    @Test
    void testCreateEmployee() {
        Employee newEmployee = new Employee("1", "John Doe", 50000, 30, "Engineer", "john@example.com");
        Employee createdEmployee = employeeService.createEmployee(newEmployee);
        assertNull(createdEmployee); // Should return null if creation fails
    }

    @Test
    void testGetHighestSalary() {
        int highestSalary = employeeService.getHighestSalaryOfEmployees();
        assertEquals(0, highestSalary); // Should return 0 if no employees
    }

    @Test
    void testGetTopTenHighestEarningEmployees() {
        List<String> topEarners = employeeService.getTopTenHighestEarningEmployeeNames();
        assertNotNull(topEarners); // Should return a list (even if empty)
    }

}
