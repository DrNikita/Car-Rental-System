package by.epam.lab.command.send;

import javax.servlet.http.HttpServletRequest;

import by.epam.lab.command.ActionCommand;
import by.epam.lab.controller.Router;
import by.epam.lab.utils.ServletPaths;

public class ToLoginPageCommand implements ActionCommand {

	@Override
	public Router execute(HttpServletRequest request) {
		return new Router(ServletPaths.LOGIN_PAGE);
	}

}
