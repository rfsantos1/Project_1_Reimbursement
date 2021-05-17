package com.revature.repo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.dto.EmployeeDTO;
import com.revature.model.Employee;
import com.revature.model.Reimbursement;
import com.revature.model.ReimbursementStatus;
import com.revature.model.ReimbursementType;
import com.revature.util.SessionUtility;

public class ReimbursementRepo {
	
	public ReimbursementRepo() {
		super();
	}
	
	public List<Reimbursement> getReimbursements(){
		Session session = SessionUtility.getSessionFactory();

		List<Reimbursement> reimbursement = new ArrayList<Reimbursement>();
		Query employeeQuery = session.createQuery("FROM Reimbursement");
		reimbursement = employeeQuery.getResultList();
		session.close();
		return reimbursement;
	}
	
	public ReimbursementStatus getReimbursementStatusById(int id) {
		Session session = SessionUtility.getSessionFactory();
		Query reimbursementQuery = session.createQuery("FROM Reimbursement_Status WHERE status_Id = :id").setParameter("id", id);
		ReimbursementStatus reimbursementStatus = (ReimbursementStatus) reimbursementQuery.getResultList();
		session.close();
		return reimbursementStatus;
	}
	
	public ReimbursementType getReimbursementTypeByString(String type) {
		Session session = SessionUtility.getSessionFactory();
		ReimbursementType reimbursementType = new ReimbursementType();
		Query reimbursementQuery = session.createQuery("FROM Reimbursement_Type WHERE type = :type").setParameter("type", type);
		reimbursementType = (ReimbursementType) reimbursementQuery.getResultList();
		session.close();
		return reimbursementType;
	}
	
	public Reimbursement addReimbursement(Reimbursement reimbursement) {
		Session session = SessionUtility.getSessionFactory();
		Transaction tx = session.beginTransaction();
		session.save(reimbursement);
		tx.commit();
		session.close();
		return reimbursement;
	}
	
	public List<Reimbursement> getReimbursementsByEmployeeId(Employee employee){
		Session session = SessionUtility.getSessionFactory();
		List<Reimbursement> reimbursements = new ArrayList<Reimbursement>();
		System.out.println(employee.toString());
		Query reimbursementQuery = session.createQuery("FROM Reimbursement WHERE employee = :id").setParameter("id", employee);
		reimbursements = reimbursementQuery.getResultList();
		session.close();
		return reimbursements;
	}
	
	public void updateReimbursement(Reimbursement reimbursement) {
		Session session = SessionUtility.getSessionFactory();
		Transaction tx = session.beginTransaction();
		session.update(reimbursement);
		tx.commit();
		session.close();
	}

}
