package by.htp.task01.service.impl;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import by.htp.task01.service.exception.ServiceException;
import by.htp.task01.service.factory.ServiceFactory;

public class TestUserServiceImpl {
	@Test
	public void signUp() throws ServiceException{
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		ServiceFactory factory = context.getBean("instanceServiceFactory", ServiceFactory.class);
		factory.getUserService().signUp("Dylan O'Brien", "3123");
		context.close();
	}
}

	

