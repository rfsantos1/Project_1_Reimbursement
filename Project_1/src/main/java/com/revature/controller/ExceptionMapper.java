package com.revature.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.exceptions.BadParameterException;
import com.revature.exceptions.LoginException;

import io.javalin.Javalin;
import io.javalin.http.ExceptionHandler;

public class ExceptionMapper implements Controller {
	
	/*
	 * https://thorben-janssen.com/hibernate-tips-difference-join-left-join-fetch-join/#JOIN
	HQL expert
	 */

	private Logger logger = LoggerFactory.getLogger(ExceptionMapper.class);
	
	private ExceptionHandler<BadParameterException> badParameterException = (e, ctx) -> {
		logger.warn(e.getMessage());
		ctx.status(400);
	};
	
	private ExceptionHandler<LoginException> loginException = (e, ctx) -> {
		
		logger.warn(e.getMessage());
		ctx.status(401);
	};

	@Override
	public void mapEndPoints(Javalin app) {
		app.exception(BadParameterException.class, badParameterException);
		app.exception(LoginException.class, loginException);
	}

}
