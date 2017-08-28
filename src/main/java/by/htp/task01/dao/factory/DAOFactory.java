package by.htp.task01.dao.factory;

import by.htp.task01.dao.BookDAO;
import by.htp.task01.dao.UserDAO;


public final class DAOFactory {
	private UserDAO userDAO;
	private BookDAO bookDAO;
	
	public DAOFactory() {}



	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}



	public void setBookDAO(BookDAO bookDAO) {
		this.bookDAO = bookDAO;
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public BookDAO getBookDAO() {
		return bookDAO;
	}

}
