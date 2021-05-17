package com.revature.repo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.dto.LoginDTO;
import com.revature.model.Employee;
import com.revature.model.EmployeeRole;
import com.revature.util.SessionUtility;

public class EmployeeRepo {
	
	public EmployeeRepo() {
		super();
	}
	
	public List<Employee> getEmployees(){
		Session session = SessionUtility.getSessionFactory();

		List<Employee> employees = new ArrayList<Employee>();
		Query employeeQuery = session.createQuery("FROM Employee");
		employees = employeeQuery.getResultList();
		session.close();
		return employees;
	}
	
	public Employee AddEmployee(Employee employee) {
		Session session = SessionUtility.getSessionFactory();
		Transaction tx = session.beginTransaction();
		session.persist(employee);
		tx.commit();
		//employees.add(employee);
		session.close();
		//Object becomes detached once you close the session
		return employee;
		
	}
	
	public Employee getUserByUsernameAndPassword(LoginDTO loginDTO) {
		List<Employee> employees = this.getEmployees();
		for (Employee u : employees) {
			if (loginDTO.getUsername().equals(u.getUsername()) && loginDTO.getPassword().equals(u.getPassword())) {
				return u;
			}
		}
		
		return null;
	}
	
	

}
