package by.epam.lab.command.user;

import javax.servlet.http.HttpServletRequest;

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
import by.epam.lab.utils.ServletPaths;

public class UserRegistration implements ActionCommand {

	private static final Logger logger = LogManager.getLogger();

	@Override
	public Router execute(HttpServletRequest request) {

		try {

			String name = request.getParameter(EntityesManager.getProperty("registration.name"));
			String secondName = request.getParameter(EntityesManager.getProperty("registration.second_name"));
			String email = request.getParameter(EntityesManager.getProperty("registration.email"));
			String phone = request.getParameter(EntityesManager.getProperty("registration.phone"));
			String password = request.getParameter(EntityesManager.getProperty("registration.password"));

			if (name != null && secondName != null && email != null && phone != null && password != null) {

				User user = new User.Builder().setName(name).setSecondName(secondName).setEmail(email).setPhone(phone)
						.setRole(Role.USER).build();

				IUserService userService = new UserServiceImpl();

				if (userService.getUserByEmail(email).isPresent()) {

					request.setAttribute("existedUser", "User with this email is already exists.");
					logger.log(Level.INFO, "incorrect email in registration form");
					return new Router(ServletPaths.REGISTRATION_PAGE);

				} else {

					userService.addUser(user, password);
					logger.log(Level.INFO, "User was registrated");
					return new Router(ServletPaths.LOGIN_PAGE);
				}

			} else {
				logger.log(Level.INFO, "Registration error");
				request.setAttribute("registrationError", "Registration error");
				return new Router(ServletPaths.LOGIN_PAGE);
			}

		} catch (ServiceLayerException e) {
			request.setAttribute("registrationError", "registration error");
			logger.log(Level.ERROR, "Service exception in " + this.getClass().getName() + ": ", e);
			return new Router(ConfigurationManager.getProperty("path.page.error"));
		}
	}
}
