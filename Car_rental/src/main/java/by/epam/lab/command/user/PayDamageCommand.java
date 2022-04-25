
package by.epam.lab.command.user;

import javax.servlet.http.HttpServletRequest;

import by.epam.lab.command.ActionCommand;
import by.epam.lab.command.router.Router;
import by.epam.lab.property_manager.EntityesManager;

public class PayDamageCommand implements ActionCommand {

	@Override
	public Router execute(HttpServletRequest request) {

		request.getSession().removeAttribute(EntityesManager.getProperty("damage"));
		return null;
	}
}
