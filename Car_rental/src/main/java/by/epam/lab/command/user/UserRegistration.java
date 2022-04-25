package by.epam.lab.command.user;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.lab.command.ActionCommand;
import by.epam.lab.command.router.ErrorRouter;
import by.epam.lab.command.router.ForwardRouter;
import by.epam.lab.command.router.Router;
import by.epam.lab.entity.User;
import by.epam.lab.entity.User.Role;
import by.epam.lab.exceptions.ServiceLayerException;
import by.epam.lab.mvc.service.IUserService;
import by.epam.lab.mvc.service.impl.UserServiceImpl;
import by.epam.lab.property_manager.ConfigurationManager;
import by.epam.lab.property_manager.EntityesManager;

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

					request.setAttribute("existedUser", "User with this email already exists.");
					logger.log(Level.INFO, "incorrect email in registration form");
					return new ForwardRouter(ConfigurationManager.getProperty("path.page.registration"));

				} else {

					userService.addUser(user, password);
					logger.log(Level.INFO, "User was registrated");
					return new ForwardRouter(ConfigurationManager.getProperty("path.page.login"));
				}

			} else {
				logger.log(Level.INFO, "Registration error");
				return new ForwardRouter(ConfigurationManager.getProperty("path.page.registration"));
			}

		} catch (ServiceLayerException e) {

			logger.log(Level.ERROR, "Service exception in " + this.getClass().getName() + ": ", e);
			return new ErrorRouter(e);
		}
	}
}
