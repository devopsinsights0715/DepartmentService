// Employee.java (in DepartmentService)
package com.department.service.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Employee {
    private Long id;
    private String name;
    private String role;

    public Employee() {}
    public Employee(Long id, String name, String role) {
        this.id = id;
        this.name = name;
        this.role = role;
    }
	@Override
	public String toString() {
		return String.format("Employee [id=%s, name=%s, role=%s]", id, name, role);
	}

    
}
