package by.htp.task01.controller.command.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp.task01.controller.command.Command;
import by.htp.task01.service.exception.ServiceException;
import by.htp.task01.service.factory.ServiceFactory;

public class SignIn implements Command {
	
private static final Logger log = LogManager.getRootLogger();
	
	
	private ServiceFactory factory;

	public ServiceFactory getFactory() {
		return factory;
	}

	public void setFactory(ServiceFactory factory) {
		this.factory = factory;
	}
	
	@Override
	public String executeCommand(String request) {
		String [] parameter = request.split(" ");
		String login = parameter[1];
		String password = parameter[2];
		
		String response = null;
		
		try {
			factory.getUserService().signIn(login, password);
			response = "Welcome " + login;
		} catch (ServiceException e) { 
			response = "Sign in error";
			log.error("fail in SignIn", e);
		}	
		return response;
	}
}

	
