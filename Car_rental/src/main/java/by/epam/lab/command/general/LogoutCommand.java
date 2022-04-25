package by.epam.lab.command.general;

import javax.servlet.http.HttpServletRequest;

import by.epam.lab.command.ActionCommand;
import by.epam.lab.command.router.RedirectRouter;
import by.epam.lab.command.router.Router;
import by.epam.lab.property_manager.ConfigurationManager;

public class LogoutCommand implements ActionCommand {

	@Override
	public Router execute(HttpServletRequest request) {

		request.getSession().invalidate();

		return new RedirectRouter(request.getContextPath() + ConfigurationManager.getProperty("path.page.login"));
	}
}
