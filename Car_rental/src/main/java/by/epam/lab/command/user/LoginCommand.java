package by.epam.lab.command.user;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.lab.command.ActionCommand;
import by.epam.lab.controller.Router;
import by.epam.lab.entity.User;
import by.epam.lab.entity.User.Role;
import by.epam.lab.exceptions.ServiceLayerException;
import by.epam.lab.mvc.service.IUserService;
import by.epam.lab.mvc.service.impl.UserServiceImpl;
import by.epam.lab.property_manager.ConfigurationManager;
import by.epam.lab.property_manager.EntityesManager;
import by.epam.lab.property_manager.MessageManager;
import by.epam.lab.utils.DAOConstants;
import by.epam.lab.utils.ServletPaths;

public class LoginCommand implements ActionCommand {

	private static final Logger logger = LogManager.getLogger();

	@Override
	public Router execute(HttpServletRequest request) {

		IUserService userDao = new UserServiceImpl();
		HttpSession session = request.getSession();
		String email = request.getParameter(DAOConstants.USER_EMAIL);
		String password = request.getParameter(DAOConstants.USER_PASSWORD);
		logger.log(Level.DEBUG, "email: " + email + " password: " + password);

		try {
			Optional<User> user = userDao.getUserByEmailPassword(email, password);
			logger.log(Level.INFO, "trying to find user by entered email: " + email);

			if (user.isPresent()) {
				logger.log(Level.INFO, "user was found.");

				Role role = user.get().getRole();

				session.setAttribute(ConfigurationManager.getProperty("user"), user.get());
				session.setAttribute(EntityesManager.getProperty("role"), role);

				if (role == Role.MANAGER) {
					return new Router(ServletPaths.MANAGER_MAIN);
				}
				return new Router(ServletPaths.MAIN);

			} else {
				request.setAttribute("errorLoginPassMessage", MessageManager.getProperty("message.loginerror"));
				return new Router(ConfigurationManager.getProperty("path.page.login"));
			}

		} catch (ServiceLayerException e) {
			logger.log(Level.ERROR, "ServiceException in method execute " + e);
			request.setAttribute("nullPage", "Page not found. Business logic error.");
			return new Router(ConfigurationManager.getProperty("path.page.error"));
		}
	}
}
