package com.revature.service;

import java.util.ArrayList;
import java.util.List;

import com.revature.dto.EmployeeDTO;
import com.revature.dto.ReimbursementDTO;
import com.revature.model.Employee;
import com.revature.model.EmployeeRole;
import com.revature.model.Reimbursement;
import com.revature.model.ReimbursementStatus;
import com.revature.model.ReimbursementType;
import com.revature.repo.ReimbursementRepo;

public class ReimbursementService {
	
	private ReimbursementRepo reimbursementRepo;
	
	public ReimbursementService() {
		this.reimbursementRepo = new ReimbursementRepo();
	}
	
	public List<Reimbursement> getReimbursements(Employee employee){
		List<Reimbursement> reimbursements = new ArrayList<Reimbursement>();
		if(employee.getEmployeeRole().getRole().equals("employee")) {
			reimbursements = reimbursementRepo.getReimbursementsByEmployeeId(employee);
		}else if(employee.getEmployeeRole().getRole().equals("financeManager")) {
			reimbursements = reimbursementRepo.getReimbursements();
		}
		
		return reimbursements;
	}
	
	public Reimbursement addReimbursement(ReimbursementDTO reimbursementDTO) {
		ReimbursementStatus reimbursementStatus = reimbursementRepo.getReimbursementStatusById(1);
		ReimbursementType reimbursementType = reimbursementRepo.getReimbursementTypeByString(reimbursementDTO.getReimbursementType());
		if(reimbursementDTO.getReimbursementType() == "traveling") {
			reimbursementType.setId(1);
			reimbursementType.setType("traveling");
		}else if(reimbursementDTO.getReimbursementType() == "food") {
			reimbursementType.setId(2);
			reimbursementType.setType("food");
		}else if(reimbursementDTO.getReimbursementType() == "lodging") {
			reimbursementType.setId(3);
			reimbursementType.setType("lodging");
		}else if(reimbursementDTO.getReimbursementType() == "other") {
			reimbursementType.setId(4);
			reimbursementType.setType("other");
		}
		Reimbursement reimbursement = new Reimbursement(reimbursementDTO.getId(), reimbursementDTO.getAmount(), reimbursementDTO.getDescription(), reimbursementDTO.getEmployee(), null, reimbursementStatus, reimbursementType);
		reimbursementRepo.addReimbursement(reimbursement);
		return reimbursement;
	}
	
	public void updateReimbursement(ReimbursementDTO reimbursementDTO) {
		Employee financeManager = new Employee();
		
		//Reimbursement reimbursement = new Reimbursement(reimbursementDTO.getId(), reimbursementDTO.getAmount(), reimbursementDTO.getDescription(), reimbursementDTO.getEmployee(), reimbursementDTO.getFinanceManager(), reimbursementDTO.getReimbursementStatus(), reimbursementDTO.getReimbursementType());
		//reimbursementRepo.updateReimbursement(reimbursement);
	}

}
