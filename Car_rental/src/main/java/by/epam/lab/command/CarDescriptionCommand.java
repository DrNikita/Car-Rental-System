package by.epam.lab.command;

import javax.servlet.http.HttpServletRequest;

import by.epam.lab.controller.Router;
import by.epam.lab.entity.Car;
import by.epam.lab.exceptions.DAOException;
import by.epam.lab.mvc_layers.dao.impl.CarDAO;
import by.epam.lab.mvc_layers.service.IService;
import by.epam.lab.utils.ConfigurationManager;
import by.epam.lab.utils.Constants;
import by.epam.lab.utils.ServletPaths;

public class CarDescriptionCommand implements ActionCommand {

	@Override
	public Router execute(HttpServletRequest request) {

		Router router = new Router();

		try {
			IService<Car> carDao = new CarDAO();
			int carId = Integer.parseInt(request.getParameter("carId"));
			Car car = carDao.getEntityById(carId).get();
			request.setAttribute(ConfigurationManager.getProperty("car"), car);
			router.setPagePath(ServletPaths.CAR_DESCRIPTION_PAGE);

		} catch (DAOException e) {
			router.setPagePath(ServletPaths.MAIN_PAGE);
		}
		return router;
	}
}
