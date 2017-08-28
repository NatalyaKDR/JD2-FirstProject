package by.htp.task01.dao;

import by.htp.task01.bean.User;
import by.htp.task01.dao.exception.DAOException;

public interface UserDAO {
	User signIn(String login, int password) throws DAOException;
	void signUp(String login, int password) throws DAOException;
}
