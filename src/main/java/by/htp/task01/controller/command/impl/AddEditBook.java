package by.htp.task01.controller.command.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp.task01.controller.command.Command;
import by.htp.task01.service.exception.ServiceException;
import by.htp.task01.service.factory.ServiceFactory;

public class AddEditBook implements Command {
	
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
		String title = parameter[1];
		String author = parameter[2];
		String genre = parameter[3];
		String year = parameter[4];
		String quantity = parameter[5];
		String idBook = parameter[6];
		
		String response = null;
		
		try {
			factory.getBookService().addEditBook(title, genre, author, year, quantity, idBook);
			response = "Book successfully edited";
		} catch (ServiceException e) {
			response = "Error editing book";
			log.error("fail in AddEditBook", e);
		}
		return response;
	}
}

	

