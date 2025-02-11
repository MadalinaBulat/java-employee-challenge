package com.reliaquest.api.controller;

import com.reliaquest.api.model.Employee;
import com.reliaquest.api.service.EmployeeService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST Controller for handling Employee-related API requests.
 * This class provides endpoints to manage employee data.
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employees")
public class EmployeeControllerImpl {

    private final EmployeeService employeeService;

    /**
     * Fetches all employees.
     * @return ResponseEntity containing a list of employees.
     */
    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    /**
     * Searches employees by name.
     * @param searchString The name to search for.
     * @return ResponseEntity containing a list of matching employees.
     */
    @GetMapping("/search/{searchString}")
    public ResponseEntity<List<Employee>> getEmployeesByNameSearch(@PathVariable String searchString) {
        return ResponseEntity.ok(employeeService.getEmployeesByNameSearch(searchString));
    }

    /**
     * Fetches an employee by their ID.
     * @param id Employee ID.
     * @return ResponseEntity containing the Employee object or 404 if not found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable String id) {
        Employee employee = employeeService.getEmployeeById(id);
        return employee != null ? ResponseEntity.ok(employee) : ResponseEntity.notFound().build();
    }

    /**
     * Fetches the highest salary among all employees.
     * @return ResponseEntity containing the highest salary.
     */
    @GetMapping("/highestSalary")
    public ResponseEntity<Integer> getHighestSalaryOfEmployees() {
        return ResponseEntity.ok(employeeService.getHighestSalaryOfEmployees());
    }

    /**
     * Fetches the top 10 highest-earning employees.
     * @return ResponseEntity containing a list of employee names.
     */
    @GetMapping("/topTenHighestEarningEmployeeNames")
    public ResponseEntity<List<String>> getTopTenHighestEarningEmployeeNames() {
        return ResponseEntity.ok(employeeService.getTopTenHighestEarningEmployeeNames());
    }

    /**
     * Creates a new employee.
     * @param employee The Employee object to be created.
     * @return ResponseEntity containing the created Employee.
     */
    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        Employee createdEmployee = employeeService.createEmployee(employee);
        return createdEmployee != null ? ResponseEntity.ok(createdEmployee) : ResponseEntity.badRequest().build();
    }

    /**
     * Deletes an employee by ID.
     * @param id The ID of the employee to be deleted.
     * @return ResponseEntity containing a success or failure message.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployeeById(@PathVariable String id) {
        return ResponseEntity.ok(employeeService.deleteEmployeeById(id));
    }
}
