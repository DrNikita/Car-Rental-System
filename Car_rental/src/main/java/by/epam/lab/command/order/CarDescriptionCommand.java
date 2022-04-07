package by.epam.lab.command.order;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.lab.command.ActionCommand;
import by.epam.lab.controller.Router;
import by.epam.lab.entity.Car;
import by.epam.lab.exceptions.ServiceLayerException;
import by.epam.lab.mvc.service.ICarService;
import by.epam.lab.mvc.service.impl.CarServiceImpl;
import by.epam.lab.property_manager.ConfigurationManager;
import by.epam.lab.property_manager.EntityesManager;
import by.epam.lab.utils.ServletPaths;

public class CarDescriptionCommand implements ActionCommand {

	private static final Logger logger = LogManager.getLogger();

	@Override
	public Router execute(HttpServletRequest request) {

		try {

			int carId = Integer.parseInt(request.getParameter(EntityesManager.getProperty("car_id")));

			ICarService carService = new CarServiceImpl();
			Optional<Car> car = carService.getEntityById(carId);

			if (car.isPresent()) {
				request.getSession().setAttribute(EntityesManager.getProperty("car"), car.get());
				return new Router(ServletPaths.CAR_DESCRIPTION_PAGE);

			} else {
				logger.log(Level.INFO, "Car  wasn't found");
				return new Router(ServletPaths.MAIN);
			}

		} catch (ServiceLayerException e) {
			logger.log(Level.ERROR, "Service exception in " + this.getClass().getName() + ": ", e);
			return new Router(ConfigurationManager.getProperty("path.page.error"));

		} catch (NumberFormatException e) {
			request.setAttribute("error", e);
			logger.log(Level.ERROR, "incorrect carId.", e);
			return new Router(ConfigurationManager.getProperty("path.page.error"));
		}
	}
}