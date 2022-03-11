package by.epam.lab.command;

import javax.servlet.http.HttpServletRequest;

import by.epam.lab.controller.Router;
import by.epam.lab.entity.Car;
import by.epam.lab.exceptions.ServiceLayerException;
import by.epam.lab.mvc_layers.service.IService;
import by.epam.lab.mvc_layers.service.impl.CarServiceImpl;
import by.epam.lab.utils.ConfigurationManager;
import by.epam.lab.utils.ServletPaths;

public class CarDescriptionCommand implements ActionCommand {

	@Override
	public Router execute(HttpServletRequest request) {

		Router router = new Router();

		try {
			IService<Car> carService = new CarServiceImpl();
			int carId = Integer.parseInt(request.getParameter("carId"));
			Car car = carService.getEntityById(carId).get();
			request.setAttribute(ConfigurationManager.getProperty("car"), car);
			router.setPagePath(ServletPaths.CAR_DESCRIPTION_PAGE);

		} catch (ServiceLayerException e) {
			router.setPagePath(ServletPaths.MAIN_PAGE);
		}
		return router;
	}
}
