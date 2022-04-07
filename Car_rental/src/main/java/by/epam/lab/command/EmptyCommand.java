package by.epam.lab.command;

import javax.servlet.http.HttpServletRequest;

import by.epam.lab.controller.Router;
import by.epam.lab.property_manager.ConfigurationManager;

public class EmptyCommand implements ActionCommand {

	@Override
	public Router execute(HttpServletRequest request) {
		return new Router(ConfigurationManager.getProperty("path.page.index"), Router.RouteType.REDIRECT);
	}
}
