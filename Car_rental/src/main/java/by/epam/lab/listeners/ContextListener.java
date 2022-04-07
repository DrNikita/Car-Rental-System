package by.epam.lab.listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import by.epam.lab.mvc.connectionpool.ConnectionPool;

@WebListener
public class ContextListener implements ServletContextListener {

	public static ConnectionPool connectionPool;

	public ContextListener() {
	}

	public void contextInitialized(ServletContextEvent sce) {
		connectionPool = ConnectionPool.getInstance();
	}

	public void contextDestroyed(ServletContextEvent sce) {
		connectionPool.destroyPool();
	}
}
