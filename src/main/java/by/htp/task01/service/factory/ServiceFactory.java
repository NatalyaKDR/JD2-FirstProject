package by.htp.task01.service.factory;

import by.htp.task01.service.BookService;
import by.htp.task01.service.UserService;

public final class ServiceFactory {

	private UserService userService;
	private BookService bookService;
	
	
	public ServiceFactory() {

	}

	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}


	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}


	public UserService getUserService() {
		return userService;
	}

	public BookService getBookService() {
		return bookService;
	}


}
