package by.epam.lab.command.send;

import javax.servlet.http.HttpServletRequest;

import by.epam.lab.command.ActionCommand;
import by.epam.lab.command.router.ForwardRouter;
import by.epam.lab.command.router.Router;
import by.epam.lab.property_manager.ConfigurationManager;

public class ToInputDatesCommand implements ActionCommand {

	@Override
	public Router execute(HttpServletRequest request) {
		return new ForwardRouter(ConfigurationManager.getProperty("path.page.input_dates"));
	}

}
