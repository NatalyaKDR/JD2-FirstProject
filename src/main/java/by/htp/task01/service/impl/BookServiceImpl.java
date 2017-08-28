package by.htp.task01.service.impl;

import java.util.IllegalFormatException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp.task01.bean.Book;
import by.htp.task01.dao.exception.DAOException;
import by.htp.task01.dao.factory.DAOFactory;
import by.htp.task01.service.BookService;
import by.htp.task01.service.exception.ServiceException;
import by.htp.task01.service.validation.ValidationData;

public class BookServiceImpl implements BookService {

	private static final Logger log = LogManager.getRootLogger();
	
	private DAOFactory factory;

	public DAOFactory getFactory() {
		return factory;
	}

	public void setFactory(DAOFactory factory) {
		this.factory = factory;
	}

	@Override
	public void addNewBook(String title, String genre, String author, String year, String quantityStr)
			throws ServiceException {
		if (!ValidationData.validBook(title, genre, author, year, quantityStr)) {
			throw new ServiceException("Incorrect data about book");
		}

		int quantity = 0;
		try {
			quantity = Integer.parseInt(quantityStr);
		} catch (IllegalFormatException e) {
			log.error("fail in BookServiceImpl", e); // лог
			throw new ServiceException("Year format exception");
		}

		try {
			factory.getBookDAO().addNewBook(title, author, genre, year, quantity);
					} catch (DAOException e) {
			log.error("fail in BookServiceImpl", e); // лог
			throw new ServiceException("Error adding a book to the library");
		}

	}

	
	@Override
	public void addEditBook(String title, String genre, String author, String year, String quantityStr,
			String idBookStr) throws ServiceException {
		if (!ValidationData.validBook(title, genre, author, year, quantityStr, idBookStr)) {
			throw new ServiceException("Incorrect data about book");
		}

		int idBook = Integer.parseInt(idBookStr);
		int quantity = Integer.parseInt(quantityStr);
		
			try {
			factory.getBookDAO().addEditBook(title, genre, author, year, quantity, idBook);
		} catch (DAOException e) {
			log.error("fail in BookServiceImpl", e);
			throw new ServiceException("Error editing book");
		}
	}

	
	@Override
	public List<Book> getBooklist() throws ServiceException {
		
		List<Book> booklist = null;

		try {
			booklist = factory.getBookDAO().getBooklist();
		} catch (DAOException e) {
			log.error("fail in BookServiceImpl", e);
			throw new ServiceException(e);
		}

		if (booklist == null) {
			throw new ServiceException("Booklist not found");
		}
		return booklist;
	}
}
	
	
	

	
	
	