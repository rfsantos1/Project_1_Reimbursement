package com.revature.app;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.controller.Controller;
import com.revature.controller.ExceptionMapper;
import com.revature.controller.LoginController;
import com.revature.util.SessionUtility;

import io.javalin.Javalin;

public class Application {
	private static Logger logger = LoggerFactory.getLogger(Application.class);
	
	public static void main(String[] args) {
		Javalin app = Javalin.create((config) -> {
			config.addStaticFiles("static");
		});
		
		mapControllers(app, new LoginController(), new ExceptionMapper());
		
		app.start(7000);
		
		Session session = SessionUtility.getSessionFactory();
	}
	
	public static void mapControllers(Javalin app, Controller... controllers) {
		for(int i = 0; i < controllers.length; i++) {
			controllers[i].mapEndPoints(app);
		}
		
	}

}
