package by.htp.task01.controller.command.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp.task01.bean.Book;
import by.htp.task01.controller.command.Command;
import by.htp.task01.service.exception.ServiceException;
import by.htp.task01.service.factory.ServiceFactory;

public class GetBookList implements Command {
	
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
		
		List<Book> booklist = null;
		String response = null;
		try {
			booklist = factory.getBookService().getBooklist();
			response = "";

			for(Book book: booklist){
				response = response + book.toString()+ "\n";
			}
		} catch (ServiceException e) {
			response = "Error getting list of books";
			log.error("fail in GetBookList", e);
		}
		return response;
	}
}
