package by.htp.task01.service.impl;

import java.io.IOException;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import by.htp.task01.dao.connection.ConnectionPool;
import by.htp.task01.dao.exception.ConnectionPoolException;
import by.htp.task01.service.exception.ServiceException;
import by.htp.task01.service.factory.ServiceFactory;


public class TestBookServiceImpl {
static ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
	ServiceFactory factory = context.getBean("instanceServiceFactory", ServiceFactory.class);

	@BeforeClass
	public static void initSource() throws ConnectionPoolException {
		ConnectionPool connectionPool = context.getBean("connectionPool", ConnectionPool.class);
		connectionPool.init();

	}

	@AfterClass
	public static void destroySource() throws ConnectionPoolException, IOException {
		ConnectionPool connectionPool = context.getBean("connectionPool", ConnectionPool.class);
		connectionPool.close();

	}

	@Test(expected = ServiceException.class)
	public void testAddNewBook() throws ServiceException {
		factory.getBookService().addNewBook(null, null, null, null, null);
	}

	@Test
	public void testAddEditBook() {
		try {
			factory.getBookService().addEditBook(null, "MyAuthor", "MyGenre", "2017", "10", "1");
		} catch (ServiceException e) {
			Assert.assertEquals("Incorrect data about book", e.getMessage());
		}
	}

}

