package by.htp.task01.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp.task01.bean.User;
import by.htp.task01.dao.exception.DAOException;
import by.htp.task01.dao.factory.DAOFactory;
import by.htp.task01.service.UserService;
import by.htp.task01.service.exception.ServiceException;
import by.htp.task01.service.validation.ValidationData;

public class UserServiceImpl implements UserService {
	
	
	private static final Logger log = LogManager.getRootLogger();
	private DAOFactory factory;

	public DAOFactory getFactory() {
		return factory;
	}

	public void setFactory(DAOFactory factory) {
		this.factory = factory;
	}
	
	
	@Override
	public void signIn(String login, String password) throws ServiceException {
		if(!ValidationData.validUser(login, password)){
			throw new ServiceException("Iccorrent user's login or password");
		}	
		
			//Attention String_paswword convert to int_password(HashCode)
		try {
			User user = factory.getUserDAO().signIn(login, password.hashCode());
			if(user == null){
				throw new ServiceException("User is not found");
			}
		} catch (DAOException e) {
			log.error("fail in UserServiceImpl", e);
			throw new ServiceException("Error sign in", e);
		}
	}
	
	
	@Override
	public void signUp(String login, String password) throws ServiceException {
		if(!ValidationData.validUser(login, password)){
			throw new ServiceException("Icorrent user's login or password");
		}

		//Attention String_paswword convert to int_password(HashCode)
		try {
			factory.getUserDAO().signUp(login, password.hashCode());
		} catch (DAOException e) {
			log.error("fail in UserServiceImpl", e);
			throw new ServiceException("Error sign up", e);
		}
	}
}
	
	
	
	
	
