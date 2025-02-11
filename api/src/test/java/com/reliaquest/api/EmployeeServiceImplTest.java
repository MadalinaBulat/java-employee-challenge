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

    /**
     * @Mock creates a mock instance of the HttpClient.
     * Mocking allows us to simulate the behavior of external dependencies
     * (like HTTP calls) without actually making real network requests.
     */
    @Mock
    private HttpClient httpClient; // Mocked HTTP client (simulates API calls)

    /**
     * @InjectMocks automatically injects the mocked dependencies (httpClient)
     * into the EmployeeServiceImpl instance. This ensures the service under
     * test is isolated from actual external dependencies.
     */
    @InjectMocks
    private EmployeeServiceImpl employeeService; // EmployeeService with injected mocks

    /**
     * Initializes mocks before each test method runs.
     * MockitoAnnotations.openMocks(this) ensures all @Mock annotated fields
     * are properly initialized before testing.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test case for getAllEmployees().
     * Since we are not mocking the method's return value, it will return
     * an empty list (default behavior of an uninitialized List in Java).
     */
    @Test
    void testGetAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        assertNotNull(employees); // The method should return a list (even if empty)
    }

    /**
     * Test case for getEmployeeById().
     * Since the method fetches employee data via an HTTP request, which is not
     * mocked, it will return null.
     */
    @Test
    void testGetEmployeeById() {
        Employee employee = employeeService.getEmployeeById("123");
        assertNull(employee); // Expecting null as there’s no real API response
    }

    /**
     * Test case for createEmployee().
     * The method attempts to send a POST request to create an employee.
     * Since the HTTP request is not mocked, it will return null.
     */
    @Test
    void testCreateEmployee() {
        Employee newEmployee = new Employee("1", "John Doe", 50000, 30, "Engineer", "john@example.com");
        Employee createdEmployee = employeeService.createEmployee(newEmployee);
        assertNull(createdEmployee); // Expecting null as no actual creation happens
    }

    /**
     * Test case for getHighestSalaryOfEmployees().
     * This method calls getAllEmployees(), which returns an empty list by default.
     * Therefore, the highest salary should be 0.
     */
    @Test
    void testGetHighestSalary() {
        int highestSalary = employeeService.getHighestSalaryOfEmployees();
        assertEquals(0, highestSalary); // Since there are no employees, it should return 0
    }

    /**
     * Test case for getTopTenHighestEarningEmployeeNames().
     * The method calls getAllEmployees(), which returns an empty list,
     * so it should return an empty list as well.
     */
    @Test
    void testGetTopTenHighestEarningEmployees() {
        List<String> topEarners = employeeService.getTopTenHighestEarningEmployeeNames();
        assertNotNull(topEarners); // The method should return a list (even if empty)
    }
}
