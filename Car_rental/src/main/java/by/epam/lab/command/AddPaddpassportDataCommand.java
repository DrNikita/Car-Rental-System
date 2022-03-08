package by.epam.lab.command;

import javax.servlet.http.HttpServletRequest;

import by.epam.lab.controller.Router;
import by.epam.lab.entity.Car;
import by.epam.lab.entity.User;
import by.epam.lab.exceptions.DAOException;
import by.epam.lab.mvc_layers.dao.impl.CarDAO;
import by.epam.lab.mvc_layers.dao.impl.UserDAO;
import by.epam.lab.mvc_layers.service.IService;
import by.epam.lab.utils.Constants;
import by.epam.lab.utils.EntityesManager;
import by.epam.lab.utils.ServletPaths;

public class AddPaddpassportDataCommand implements ActionCommand {

	@Override
	public Router execute(HttpServletRequest request) {

		Router router = new Router();

		try {
			IService<User> userDao = new UserDAO();

			String passpotrNumber = request.getParameter(EntityesManager.getProperty("passport_num"));
			String passportIdNumber = request.getParameter(EntityesManager.getProperty("passport_id_num"));

			int userId = ((User) (request.getSession().getAttribute(EntityesManager.getProperty("user")))).getId();
			((UserDAO) (userDao)).changePassportData(passpotrNumber, passportIdNumber, userId);

			router.setPagePath(ServletPaths.INPUT_DATES_PAGE);

		} catch (DAOException e) {
		}

		return router;
	}
}
