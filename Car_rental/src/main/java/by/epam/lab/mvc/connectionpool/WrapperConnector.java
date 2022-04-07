package by.epam.lab.mvc.connectionpool;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.lab.exceptions.TransactionException;
import by.epam.lab.listeners.ContextListener;

import java.sql.Connection;
import java.sql.SQLException;

public class WrapperConnector implements AutoCloseable {

	private static final Logger logger = LogManager.getLogger();

	private final Connection connection;
//	private final static ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

	public WrapperConnector() {
//		connection = CONNECTION_POOL.getConnection();
		connection = ContextListener.connectionPool.getConnection();
	}

	public Connection getConnection() {
		return connection;
	}

	public void startTransaction() throws TransactionException {
		try {
			connection.setAutoCommit(false);
		} catch (SQLException e) {
			logger.log(Level.ERROR, e);
			throw new TransactionException(e);
		}
	}

	public void commitTransaction() throws TransactionException {
		try {
			connection.commit();
		} catch (SQLException e) {
			logger.log(Level.ERROR, e);
			throw new TransactionException(e);
		}
	}

	public void rollbackTransaction() throws TransactionException {
		try {
			connection.rollback();
		} catch (SQLException e) {
			logger.log(Level.ERROR, e);
			throw new TransactionException(e);
		}
	}

	public void endTransaction() throws TransactionException {
		try {
			connection.setAutoCommit(true);
		} catch (SQLException e) {
			logger.log(Level.ERROR, e);
			throw new TransactionException(e);
		}
	}

	public void close() {
//		CONNECTION_POOL.releaseConnection(connection);
		ContextListener.connectionPool.releaseConnection(connection);
	}
}
