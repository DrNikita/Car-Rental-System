package by.epam.lab.command;

import javax.servlet.http.HttpServletRequest;

import by.epam.lab.property_manager.MessageManager;

public class ActionFactory {
	public ActionCommand defineCommand(HttpServletRequest request) {

		ActionCommand current = new EmptyCommand();

		String action = request.getParameter("command");

		if (action == null || action.isEmpty()) {
			return current;

		} else {

			try {
				CommandEnum currentEnum = CommandEnum.valueOf(action.toUpperCase());
				current = currentEnum.getCurrentCommand();

			} catch (IllegalArgumentException e) {
				request.setAttribute("wrongAction", action + MessageManager.getProperty("message.wrongaction"));
			}

			return current;
		}
	}
}
