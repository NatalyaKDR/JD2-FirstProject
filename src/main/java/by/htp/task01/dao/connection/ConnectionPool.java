package by.htp.task01.dao.connection;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp.task01.dao.exception.ConnectionPoolException;
import by.htp.task01.dao.exception.DAOException;

public final class ConnectionPool implements Closeable{	
	
	private static final Logger log = LogManager.getRootLogger();
	

	
	private BlockingQueue<Connection> freeConnection;
	private BlockingQueue<Connection> busyConnection;
	
	private int poolsize;
	private String driver;
	private String user;
	private String password;
	private String url;
 	
	
	public ConnectionPool() {    //конструктор без пар-ов
	}

	
	
	public ConnectionPool(int poolsize, String driver, String user, String password, String url) {
		this.driver = driver;
		this.user = user;
		this.password = password;
		this.url = url;

		try {
			this.poolsize = poolsize;
		} catch (NumberFormatException e) {
			log.error("fail in ConnectionPool", e);
			this.poolsize = 6;
		}
	}                                  //конструктор с пар-ми
	
	
		public void init() throws ConnectionPoolException{
		freeConnection = new ArrayBlockingQueue<Connection>(poolsize);
		busyConnection = new ArrayBlockingQueue<Connection>(poolsize);
		
		try{
			Class.forName(driver);
			for(int i = 0; i < poolsize; i++){
				freeConnection.add(DriverManager.getConnection(url, user, password));
			}
		}catch (ClassNotFoundException e) {
			log.error("fail in ConnectionPool", e); //добавляю лог
			throw new ConnectionPoolException("Can't find database driver class", e);
		} catch (SQLException e) {
			log.error("fail in ConnectionPool", e); //добавляю лог
			throw new ConnectionPoolException("SQLException in ConnectionPool", e);
		}
		
	}
		
	
	
	public Connection take() throws ConnectionPoolException{
		Connection connection = null;
		try {
			connection = freeConnection.take();
			busyConnection.put(connection);
		} catch (InterruptedException e) {
			log.error("fail in ConnectionPool", e); //лог
			throw new ConnectionPoolException("Error connecting to the data source", e);
		}
		return connection;
	}
	
	public void free(Connection connection) throws InterruptedException, DAOException{
		if(connection == null){
			throw new DAOException("Connection is null");
		}
		
		Connection tempConnection = connection;
		connection = null;
		busyConnection.remove(tempConnection);
		freeConnection.put(tempConnection);
	}
	
	
	
	/*public static ConnectionPool getInstance(){
		return instance;
	}
	*/
	
	@Override
	public void close() throws IOException {
		List<Connection> listConnection = new ArrayList<Connection>();
		listConnection.addAll(this.busyConnection);
		listConnection.addAll(this.freeConnection);

		for (Connection connection : listConnection) {
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				log.error("fail in ConnectionPool", e);
			}
		}
	}

	public void closeConnection(Connection con, Statement st, PreparedStatement preSt, ResultSet rs) {
		if (con != null) {
			try {
				free(con);
			} catch (InterruptedException | DAOException e) {
				log.error("Connection isn't return to the pool", e);
			}
		}

		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				log.error("Statement isn't closed", e);
			}
		}

		if (preSt != null) {
			try {
				preSt.close();
			} catch (SQLException e) {
				log.error("PrepareStatement ins't closed", e);
			}
		}

		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				log.error("ResultSet ins't closed", e);
			}
		}
	}

	public void closeConnection(Connection con, Statement st) {
		if (con != null) {
			try {
				free(con);
			} catch (InterruptedException | DAOException e) {
				log.error("Connection isn't return to the pool", e);
			}
		}

		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				log.error("Statement isn't closed", e);
			}
		}
	}

	public void closeConnection(Connection con, PreparedStatement preSt) {
		if (con != null) {
			try {
				free(con);
			} catch (InterruptedException | DAOException e) {
				log.error("Connection isn't return to the pool", e);
			}
		}

		if (preSt != null) {
			try {
				preSt.close();
			} catch (SQLException e) {
				log.error("PrepareStatement ins't closed", e);
			}
		}
	}

	public void closeConnection(Connection con, ResultSet rs) {
		if (con != null) {
			try {
				free(con);
			} catch (InterruptedException | DAOException e) {
				log.error("Connection isn't return to the pool", e);
			}
		}

		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				log.error("ResultSet ins't closed", e);
			}
		}
	}

	public void closeConnection(Connection con, Statement st, PreparedStatement preSt) {
		if (con != null) {
			try {
				free(con);
			} catch (InterruptedException | DAOException e) {
				log.error("Connection isn't return to the pool", e);
			}
		}

		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				log.error("Statement isn't closed", e);
			}
		}

		if (preSt != null) {
			try {
				preSt.close();
			} catch (SQLException e) {
				log.error("PrepareStatement ins't closed", e);
			}
		}

	}

	public void closeConnection(Connection con, PreparedStatement preSt, ResultSet rs) {
		if (con != null) {
			try {
				free(con);
			} catch (InterruptedException | DAOException e) {
				log.error("Connection isn't return to the pool", e);
			}
		}

		if (preSt != null) {
			try {
				preSt.close();
			} catch (SQLException e) {
				log.error("PrepareStatement ins't closed", e);
			}
		}

		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				log.error("ResultSet ins't closed", e);
			}
		}
	}

	public void closeConnection(Connection con, Statement st, ResultSet rs) {
		if (con != null) {
			try {
				free(con);
			} catch (InterruptedException | DAOException e) {
				log.error("Connection isn't return to the pool", e);
			}
		}

		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				log.error("Statement isn't closed", e);
			}
		}

		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				log.error("ResultSet ins't closed", e);
			}
		}
	}

}
