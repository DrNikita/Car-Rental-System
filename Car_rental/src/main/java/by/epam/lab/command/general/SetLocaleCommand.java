package by.epam.lab.command.general;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import by.epam.lab.command.ActionCommand;
import by.epam.lab.command.router.ForwardRouter;
import by.epam.lab.command.router.Router;
import by.epam.lab.property_manager.ConfigurationManager;
import by.epam.lab.property_manager.EntityesManager;

public class SetLocaleCommand implements ActionCommand {

	@Override
	public Router execute(HttpServletRequest request) {

		String language = request.getParameter(EntityesManager.getProperty("language"));

		Locale locale;

		if (language != null) {
			locale = new Locale(language);
		} else {
			locale = new Locale(EntityesManager.getProperty("language.en"));
		}

		request.getSession().setAttribute(EntityesManager.getProperty("locale"), locale);

		return new ForwardRouter(ConfigurationManager.getProperty("path.page.login"));
	}
}
