package by.epam.lab.command.user;

import javax.servlet.http.HttpServletRequest;

import by.epam.lab.command.ActionCommand;
import by.epam.lab.controller.Router;
import by.epam.lab.property_manager.ConfigurationManager;

public class PayOrderCommand implements ActionCommand {

	@Override
	public Router execute(HttpServletRequest request) {
		return new Router(ConfigurationManager.getProperty("path.page.main"));
	}

}
