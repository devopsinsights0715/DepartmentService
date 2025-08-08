// DepartmentController.java
package com.department.service.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.department.service.model.Employee;

@RestController
@RequestMapping("/department")
public class DepartmentController {

	private static final Logger logger = LoggerFactory.getLogger(DepartmentController.class);
	
    private final RestTemplate restTemplate = new RestTemplate();

    @PostMapping("/create-employee")
    public String createEmployeeFromDepartment(@RequestBody Employee employee) {
    	logger.info("Received POST request to create employee: {}", employee);
    	
        String employeeServiceUrl = "http://localhost:8081/employees/addemployee";
        logger.info("employeeServiceUrl: {}", employeeServiceUrl);
        
        ResponseEntity<Employee> response = restTemplate.postForEntity(employeeServiceUrl, employee, Employee.class);
        
        logger.info("response: {}", response.toString()); 
        
        if (response.getStatusCode() == HttpStatus.OK || response.getStatusCode() == HttpStatus.CREATED) {
        	logger.info("Employee created successfully in EmployeeService: {}", response.toString());
            return "Employee created successfully in EmployeeService: " + response.getBody();
        } else {
        	logger.info("Failed to create employee: {}", response.toString());
            return "Failed to create employee";
        }
    }
}
