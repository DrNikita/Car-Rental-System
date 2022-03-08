package by.epam.lab.command;

import javax.servlet.http.HttpServletRequest;

import by.epam.lab.controller.Router;
import by.epam.lab.entity.User;
import by.epam.lab.mvc_layers.dao.impl.UserDAO;
import by.epam.lab.mvc_layers.service.IService;
import by.epam.lab.utils.EntityesManager;
import by.epam.lab.utils.ServletPaths;

public class CheckPassportDataCommand implements ActionCommand {

	@Override
	public Router execute(HttpServletRequest request) {

		Router router = new Router();

		User user = (User) request.getSession().getAttribute(EntityesManager.getProperty("user"));

		if (user.isPassportDataExists()) {
			router.setPagePath(ServletPaths.INPUT_DATES_PAGE);
		} else {
			router.setPagePath(ServletPaths.INPUT_PASSPORT_DATA_PAGE);
		}

		return router;
	}
}
