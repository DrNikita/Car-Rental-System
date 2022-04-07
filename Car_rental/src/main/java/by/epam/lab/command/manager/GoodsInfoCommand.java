package by.epam.lab.command.manager;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.lab.command.ActionCommand;
import by.epam.lab.controller.Router;
import by.epam.lab.entity.Car;
import by.epam.lab.exceptions.ServiceLayerException;
import by.epam.lab.mvc.service.IService;
import by.epam.lab.mvc.service.impl.CarServiceImpl;
import by.epam.lab.property_manager.ConfigurationManager;
import by.epam.lab.property_manager.EntityesManager;

public class GoodsInfoCommand implements ActionCommand {

	private static final Logger logger = LogManager.getLogger();

	@Override
	public Router execute(HttpServletRequest request) {

		try {

			IService<Car> carService = new CarServiceImpl();
			int carId = Integer.parseInt(request.getParameter(EntityesManager.getProperty("car_id")));
			Optional<Car> car = carService.getEntityById(carId);

			if (car.isPresent()) {
				request.getSession().setAttribute(EntityesManager.getProperty("goods"), car.get());
				return new Router(ConfigurationManager.getProperty("path.page.goods_info"));

			} else {
				logger.log(Level.INFO, "Car with id: " + carId + "wasn't found");
				return new Router(ConfigurationManager.getProperty("page.path.goods"));
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
