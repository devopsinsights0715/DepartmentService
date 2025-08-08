// DepartmentController.java
package com.example.departmentservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.departmentservice.model.Employee;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    private final RestTemplate restTemplate = new RestTemplate();

    @PostMapping("/create-employee")
    public String createEmployeeFromDepartment(@RequestBody Employee employee) {
        String employeeServiceUrl = "http://localhost:8081/employees";

        ResponseEntity<Employee> response = restTemplate.postForEntity(employeeServiceUrl, employee, Employee.class);

        if (response.getStatusCode() == HttpStatus.OK || response.getStatusCode() == HttpStatus.CREATED) {
            return "Employee created successfully in EmployeeService: " + response.getBody();
        } else {
            return "Failed to create employee";
        }
    }
}
