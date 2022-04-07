package by.epam.lab.command.send;

import javax.servlet.http.HttpServletRequest;

import by.epam.lab.command.ActionCommand;
import by.epam.lab.controller.Router;
import by.epam.lab.property_manager.ConfigurationManager;

public class ToRegisterDamageCommand implements ActionCommand {

	@Override
	public Router execute(HttpServletRequest request) {
		return new Router(ConfigurationManager.getProperty("path.page.to_register_damage"));
	}
}
