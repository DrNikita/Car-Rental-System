package by.epam.lab.mvc_layers.connectionpool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConnectionPool {
	public static final Logger logger = LogManager.getLogger();
	private static final int DEFAULT_POOL_SIZE = 8;
	private static AtomicBoolean isCreated = new AtomicBoolean();
	private static ConnectionPool instance = new ConnectionPool();
	private static Lock locker = new ReentrantLock(true);
	private BlockingQueue<Connection> freeConnection;
	private Queue<Connection> givenAwayConnections;

	private ConnectionPool() {
		freeConnection = new LinkedBlockingDeque<>(DEFAULT_POOL_SIZE);
		givenAwayConnections = new ArrayDeque<>();
		logger.log(Level.INFO, "Try to create connection pool");
		for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
			try {
				Connection connection = ConnectionFactory.getConnection();
				boolean isAdded = freeConnection.add(connection);

			} catch (SQLException e) {
				logger.log(Level.ERROR, "coudn't create connection to data base: " + e.getMessage());
			}
		}
		if (freeConnection.size() == 0) {
			logger.log(Level.FATAL, "connections poll don't created, pool size: " + freeConnection.size());
			throw new RuntimeException("connections pull don't created");
		}
		logger.log(Level.INFO, "Connection pool was created");
	}

	public static ConnectionPool getInstance() {
		if (!isCreated.get()) {
			locker.lock();
			if (instance == null) {
				instance = new ConnectionPool();
				isCreated.set(true);
			}
			locker.unlock();
		}
		return instance;
	}

	public Connection getConnection() {
		Connection connection = null;
		try {
			connection = freeConnection.take();
			logger.log(Level.DEBUG, "Gave connection " + connection);
			givenAwayConnections.offer(connection);
		} catch (InterruptedException e) {
			logger.log(Level.ERROR, "InterruptedException in method getConnection " + e.getMessage());
			Thread.currentThread().interrupt();
		}
		return connection;
	}

	public void releaseConnection(Connection connection) {
		if (givenAwayConnections.remove(connection)) {
			try {
				freeConnection.put(connection);
			} catch (InterruptedException e) {
				logger.log(Level.ERROR, "InterruptedException in method getConnection " + e.getMessage());
				Thread.currentThread().interrupt();
			}
		}
	}

	public void destroyPool() {
		for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
			try {
				logger.log(Level.DEBUG, "destroyPool");
				freeConnection.take().close();
			} catch (InterruptedException e) {
				logger.log(Level.ERROR, "InterruptedException in method destroyPool " + e.getMessage());
			} catch (SQLException e) {
				logger.log(Level.ERROR, "SQLException in method destroyPool " + e.getMessage());
			}
		}
		deregisterDrivers();
	}

	private void deregisterDrivers() {
		DriverManager.getDrivers().asIterator().forEachRemaining(driver -> {
			try {
				DriverManager.deregisterDriver(driver);
			} catch (SQLException e) {
				logger.log(Level.ERROR, "SQLException in method deregisterDrivers " + e.getMessage());
			}
		});
	}
}
