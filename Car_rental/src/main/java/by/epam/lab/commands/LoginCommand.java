package by.epam.lab.commands;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.lab.authorization.MessageManager;
import by.epam.lab.entity.User;
import by.epam.lab.exceptions.DAOException;
import by.epam.lab.mvc_layers.dao.impl.UserDAOImpl;
import by.epam.lab.utils.ConfigurationManager;
import by.epam.lab.utils.Constants;
import by.epam.lab.utils.Encode;

public class LoginCommand implements ActionCommand {

	private static final Logger logger = LogManager.getLogger();
	private UserDAOImpl userService = new UserDAOImpl();

	@Override
	public String execute(HttpServletRequest request) {

		Optional<User> user;
		HttpSession session = request.getSession();
		String page = null;
		String email = request.getParameter(Constants.USER_EMAIL);
		String pass = request.getParameter(Constants.PASSWORD);
		logger.log(Level.DEBUG, "email: " + email + " password: " + pass);

		try {
			user = userService.getUserByEmail(email);
			logger.log(Level.INFO, "trying to find user by entered email: " + email);

			if (user.isPresent()) {
				logger.log(Level.INFO, "user was found.");
				if (userService.getPasswordByEmail(email).get().equals(Encode.encodePassword(pass))) {
					session.setAttribute("user", user.get());
					page = ConfigurationManager.getProperty("path.servlet.main");

				} else {
					request.setAttribute("errorLoginPassMessage", MessageManager.getProperty("message.loginerror"));
					page = ConfigurationManager.getProperty("path.page.login");
				}
			} else {
				request.setAttribute("errorLoginPassMessage", MessageManager.getProperty("message.loginerror"));
				page = ConfigurationManager.getProperty("path.page.login");
			}

		} catch (DAOException e) {
			logger.log(Level.ERROR, "DAOException in method execute " + e);
			request.setAttribute("nullPage", "Page not found. Business logic error.");
		}

		return page;
	}
}
