package by.epam.lab.command;

import javax.servlet.http.HttpServletRequest;

import by.epam.lab.command.router.RedirectRouter;
import by.epam.lab.command.router.Router;
import by.epam.lab.entity.User.Role;
import by.epam.lab.property_manager.ConfigurationManager;
import by.epam.lab.property_manager.EntityesManager;

public class EmptyCommand implements ActionCommand {

	@Override
	public Router execute(HttpServletRequest request) {

		request.getSession().removeAttribute(EntityesManager.getProperty("user"));
		request.getSession().setAttribute(EntityesManager.getProperty("role"), Role.GUEST);
		request.getSession().removeAttribute(EntityesManager.getProperty("car"));
		request.getSession().removeAttribute(EntityesManager.getProperty("order"));
		request.getSession().removeAttribute(EntityesManager.getProperty("goods"));

		return new RedirectRouter(request.getContextPath() + ConfigurationManager.getProperty("path.page.index"));
	}
}
