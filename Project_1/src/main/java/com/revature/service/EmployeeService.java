package com.revature.service;

import com.revature.repo.EmployeeRepo;

public class EmployeeService {
	
	public EmployeeRepo employeeRepo;
	
	public EmployeeService() {
		this.employeeRepo = new EmployeeRepo();
	}
	
	public EmployeeService(EmployeeRepo employeeRepo) {
		this.employeeRepo = employeeRepo;
	}
	
	

}
