package by.epam.lab.command.general;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import by.epam.lab.command.ActionCommand;
import by.epam.lab.controller.Router;
import by.epam.lab.property_manager.EntityesManager;
import by.epam.lab.utils.ServletPaths;

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

		return new Router(ServletPaths.LOGIN_PAGE);
	}
}
