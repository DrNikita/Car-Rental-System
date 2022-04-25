package by.epam.lab.listeners;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebListener;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.lab.mvc.connectionpool.ConnectionPool;

@WebListener
public class ContextListener implements ServletContextListener {

	private static final Logger logger = LogManager.getLogger();

	public static ConnectionPool connectionPool;

	public ContextListener() {
	}

	public void contextInitialized(ServletContextEvent sce) {
		try {
			connectionPool = ConnectionPool.getInstance();

		} catch (SQLException e) {
			logger.log(Level.ERROR, "Failed to start the application.", e);

			ServletContext context = sce.getServletContext();

			try {
				context.getRequestDispatcher(null).forward(null, null);

			} catch (ServletException | IOException e1) {
			}
		}
	}

	public void contextDestroyed(ServletContextEvent sce) {
		connectionPool.destroyPool();
	}
}
