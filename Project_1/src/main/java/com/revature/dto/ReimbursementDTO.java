package com.revature.dto;

import com.revature.model.Employee;
import com.revature.model.ReimbursementStatus;
import com.revature.model.ReimbursementType;

public class ReimbursementDTO {
	
	private int id;
	private double amount;
	private String description;
	private Employee employee;
	private String reimbursementType;
	
	public ReimbursementDTO() {
		super();
	}
	
	public ReimbursementDTO(int id, double amount, String description, Employee employee, String reimbursementType) {
		this.id = id;
		this.amount = amount;
		this.description = description;
		this.employee = employee;
		this.reimbursementType = reimbursementType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((employee == null) ? 0 : employee.hashCode());
		result = prime * result + id;
		result = prime * result + ((reimbursementType == null) ? 0 : reimbursementType.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReimbursementDTO other = (ReimbursementDTO) obj;
		if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (employee == null) {
			if (other.employee != null)
				return false;
		} else if (!employee.equals(other.employee))
			return false;
		if (id != other.id)
			return false;
		if (reimbursementType == null) {
			if (other.reimbursementType != null)
				return false;
		} else if (!reimbursementType.equals(other.reimbursementType))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ReimbursementDTO [id=" + id + ", amount=" + amount + ", description=" + description + ", employee="
				+ employee + ", reimbursementType=" + reimbursementType + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public String getReimbursementType() {
		return reimbursementType;
	}

	public void setReimbursementType(String reimbursementType) {
		this.reimbursementType = reimbursementType;
	}
	
	

}
