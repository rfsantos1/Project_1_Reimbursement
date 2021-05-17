package com.revature.controller;

import java.util.List;

import com.revature.dto.EmployeeDTO;
import com.revature.dto.LoginDTO;
import com.revature.dto.MessageDTO;
import com.revature.dto.ReimbursementDTO;
import com.revature.model.Employee;
import com.revature.model.EmployeeRole;
import com.revature.model.Reimbursement;
import com.revature.service.LoginService;
import com.revature.service.ReimbursementService;

import io.javalin.Javalin;
import io.javalin.http.Handler;

public class LoginController implements Controller {
	
	private LoginService loginService;
	private ReimbursementService reimbursementService;
	
	public LoginController() {
		this.loginService = new LoginService();
		this.reimbursementService = new ReimbursementService();
	}
	
	private Handler loginHandler = (ctx) -> {
		LoginDTO loginDTO = ctx.bodyAsClass(LoginDTO.class);
		
		Employee employee = loginService.login(loginDTO);
		
		ctx.sessionAttribute("LoggedInUser", employee);
		if(employee.getEmployeeRole().getRole().equals("employee")) {
			ctx.status(200);
		}else if(employee.getEmployeeRole().getRole().equals("financeManager")) {
			ctx.status(201);
		}
		ctx.json(employee);
	};
	
	private Handler currentUserHandler = (ctx) -> {
		Employee employee = (Employee) ctx.sessionAttribute("LoggedInUser");
		
		if (employee == null) {
			MessageDTO messageDTO = new MessageDTO();
			messageDTO.setMessage("User is not currently logged in!");
			ctx.json(messageDTO);
			ctx.status(400);
		} else {
			ctx.json(employee);
		}
		
	};
	
	private Handler logoutHandler = (ctx) -> {
		ctx.req.getSession().invalidate();
	};
	
	private Handler addReimbursementHandler = (ctx) -> {
		ReimbursementDTO reimbursementDTO = (ReimbursementDTO) ctx.bodyAsClass(ReimbursementDTO.class);
		
		Reimbursement reimbursement = reimbursementService.addReimbursement(reimbursementDTO);
		ctx.status(200);
		ctx.json(reimbursement);
	};
	
	private Handler getAllReimbursementsByEmployeeId = (ctx) -> {
		Employee employee = (Employee) ctx.sessionAttribute("LoggedInUser");
		//(String username, String password, String firstName, String lastName, String email, EmployeeRole employeeRole)
		List<Reimbursement> reimbursements = reimbursementService.getReimbursements(employee);
		if(reimbursements == null) {
			MessageDTO messageDTO = new MessageDTO();
			messageDTO.setMessage("No reimbursements were pulled!");
			ctx.json(messageDTO);
			ctx.status(400);
		}else {
		ctx.status(200);
		ctx.json(reimbursements);
		}
	};
	
	private Handler updateStatusOfReimbursement = (ctx) -> {
		ReimbursementDTO reimbursementDTO = (ReimbursementDTO) ctx.bodyAsClass(ReimbursementDTO.class);
		
		reimbursementService.updateReimbursement(reimbursementDTO);
		ctx.status(200);
	};

	@Override
	public void mapEndPoints(Javalin app) {
		// TODO Auto-generated method stub

		app.post("/login", loginHandler);
		app.get("/current_user", currentUserHandler);
		app.post("/logout", logoutHandler);
		app.get("/reimbursements", getAllReimbursementsByEmployeeId);
		app.put("/updateReimbursement", updateStatusOfReimbursement);
		app.post("/addReimbursement", addReimbursementHandler);
	}

}
