package by.epam.lab.command;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.lab.controller.Router;
import by.epam.lab.entity.User;
import by.epam.lab.exceptions.ServiceLayerException;
import by.epam.lab.mvc_layers.service.IService;
import by.epam.lab.mvc_layers.service.impl.UserServiceImpl;
import by.epam.lab.utils.ConfigurationManager;
import by.epam.lab.utils.DAOConstants;
import by.epam.lab.utils.MessageManager;
import by.epam.lab.utils.ServletPaths;

public class LoginCommand implements ActionCommand {

	private static final Logger logger = LogManager.getLogger();

	@Override
	public Router execute(HttpServletRequest request) {

		IService<User> userDao = new UserServiceImpl();
		HttpSession session = request.getSession();
		String email = request.getParameter(DAOConstants.USER_EMAIL);
		String password = request.getParameter(DAOConstants.USER_PASSWORD);
		logger.log(Level.DEBUG, "email: " + email + " password: " + password);
		Router router = new Router();

		try {
			Optional<User> user = (((UserServiceImpl) userDao).getUserByEmailPassword(email, password));
			logger.log(Level.INFO, "trying to find user by entered email: " + email);

			if (user.isPresent()) {
				logger.log(Level.INFO, "user was found.");
				session.setAttribute(ConfigurationManager.getProperty("user"), user.get());
				router = new Router(ServletPaths.MAIN);

			} else {
				request.setAttribute("errorLoginPassMessage", MessageManager.getProperty("message.loginerror"));
				router = new Router(ConfigurationManager.getProperty("path.page.index"));
			}

		} catch (ServiceLayerException e) {
			logger.log(Level.ERROR, "DAOException in method execute " + e);
			request.setAttribute("nullPage", "Page not found. Business logic error.");
		}
		return router;
	}
}
