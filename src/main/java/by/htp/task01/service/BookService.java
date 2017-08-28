package by.htp.task01.service;

import java.util.List;

import by.htp.task01.bean.Book;
import by.htp.task01.service.exception.ServiceException;

public interface BookService {
	void addNewBook(String title, String genre, String author, String year, String quantity) throws ServiceException;
	void addEditBook(String title, String genre, String author, String year, String quantity, String idBook) throws ServiceException;
	List<Book> getBooklist() throws ServiceException;
}
