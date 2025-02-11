package com.reliaquest.api.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.reliaquest.api.model.Employee;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Implementation of the EmployeeService interface.
 * Handles business logic and communication with the backend API.
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final String BASE_URL = "http://localhost:8112/api/v1/employee";
    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Fetches all employees from the backend API.
     * @return List of Employee objects.
     */
    @Override
    public List<Employee> getAllEmployees() {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL))
                    .GET()
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            Map<String, Object> responseMap = objectMapper.readValue(response.body(), Map.class);
            return objectMapper.convertValue(responseMap.get("data"), new TypeReference<List<Employee>>() {});
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    /**
     * Fetches an employee by ID from the backend API.
     * @param id Employee ID.
     * @return Employee object if found, otherwise null.
     */
    @Override
    public Employee getEmployeeById(String id) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + "/" + id))
                    .GET()
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            Map<String, Object> responseMap = objectMapper.readValue(response.body(), Map.class);
            return objectMapper.convertValue(responseMap.get("data"), Employee.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Creates a new employee via the backend API.
     * @param employee The Employee object to create.
     * @return The created Employee or null if creation fails.
     */
    @Override
    public Employee createEmployee(Employee employee) {
        try {
            String jsonBody = objectMapper.writeValueAsString(employee);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            Map<String, Object> responseMap = objectMapper.readValue(response.body(), Map.class);
            return objectMapper.convertValue(responseMap.get("data"), Employee.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Deletes an employee by ID via the backend API.
     * @param id Employee ID.
     * @return Success or failure message.
     */
    @Override
    public String deleteEmployeeById(String id) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + "/" + id))
                    .DELETE()
                    .build();

            httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            return "Employee deleted successfully.";
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to delete employee.";
        }
    }

    @Override
    public int getHighestSalaryOfEmployees() {
        return getAllEmployees().stream()
                .mapToInt(Employee::getSalary)
                .max()
                .orElse(0);
    }

    @Override
    public List<String> getTopTenHighestEarningEmployeeNames() {
        return getAllEmployees().stream()
                .sorted((a, b) -> Integer.compare(b.getSalary(), a.getSalary()))
                .limit(10)
                .map(Employee::getName)
                .toList();
    }

    @Override
    public List<Employee> getEmployeesByNameSearch(String name) {
        return getAllEmployees().stream()
                .filter(emp -> emp.getName().toLowerCase().contains(name.toLowerCase()))
                .toList();
    }
}
