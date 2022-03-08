package by.epam.lab.command;

import javax.servlet.http.HttpServletRequest;

import by.epam.lab.controller.Router;
import by.epam.lab.utils.ConfigurationManager;

public class EmptyCommand implements ActionCommand {

	@Override
	public Router execute(HttpServletRequest request) {
		Router router = new Router(ConfigurationManager.getProperty("path.page.login"));
		return router;
	}
}
