package by.epam.lab.command.user;

import javax.servlet.http.HttpServletRequest;

import by.epam.lab.command.ActionCommand;
import by.epam.lab.controller.Router;
import by.epam.lab.entity.User.Role;
import by.epam.lab.property_manager.ConfigurationManager;
import by.epam.lab.property_manager.EntityesManager;

public class LogoutCommand implements ActionCommand {

	@Override
	public Router execute(HttpServletRequest request) {

		request.getSession().removeAttribute(EntityesManager.getProperty("user"));
		request.getSession().setAttribute(EntityesManager.getProperty("role"), Role.GUEST);
		request.getSession().removeAttribute(EntityesManager.getProperty("car"));
		request.getSession().removeAttribute(EntityesManager.getProperty("order"));
		request.getSession().removeAttribute(EntityesManager.getProperty("goods"));

		return new Router(ConfigurationManager.getProperty("path.page.login"));
	}
}
