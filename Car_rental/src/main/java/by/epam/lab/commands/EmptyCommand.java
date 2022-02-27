package by.epam.lab.commands;

import javax.servlet.http.HttpServletRequest;

import by.epam.lab.utils.ConfigurationManager;

public class EmptyCommand implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request) {
		String page = ConfigurationManager.getProperty("path.page.login");
		return page;
	}

}
