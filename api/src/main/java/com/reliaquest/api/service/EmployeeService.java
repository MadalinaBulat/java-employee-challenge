package com.reliaquest.api.service;

import com.reliaquest.api.model.Employee;
import java.util.List;

/**
 * Service interface for managing Employee operations.
 */
public interface EmployeeService {
    List<Employee> getAllEmployees();

    List<Employee> getEmployeesByNameSearch(String name);

    Employee getEmployeeById(String id);

    int getHighestSalaryOfEmployees();

    List<String> getTopTenHighestEarningEmployeeNames();

    Employee createEmployee(Employee employee);

    String deleteEmployeeById(String id);
}
