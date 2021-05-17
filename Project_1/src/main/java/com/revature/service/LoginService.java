package com.revature.service;


import com.revature.dto.LoginDTO;
import com.revature.exceptions.BadParameterException;
import com.revature.exceptions.LoginException;
import com.revature.model.Employee;
import com.revature.repo.EmployeeRepo;

public class LoginService {
	
	private EmployeeRepo employeeRepo;
	
	public LoginService() {
		this.employeeRepo = new EmployeeRepo();
	}
	
	// This one is for Mockito
	public LoginService(EmployeeRepo employeeRepo) {
		this.employeeRepo = employeeRepo;
	}
	
	public Employee login(LoginDTO loginDTO) throws BadParameterException, LoginException {
		// Do some checking for blank username, blank password
		if (loginDTO.getUsername().trim().equals("") || loginDTO.getPassword().trim().equals("")) {
			throw new BadParameterException("Cannot have blank username and/or password");
		}
		
		Employee employee = employeeRepo.getUserByUsernameAndPassword(loginDTO);
		
		if (employee == null) {
			throw new LoginException("User was not able to login in with the supplied username and password");
		}
		
		return employee;
	}

}
