package by.epam.lab.command.manager;

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

public class GetGoodsCommand implements ActionCommand {

	private static final Logger logger = LogManager.getLogger();

	@Override
	public Router execute(HttpServletRequest request) {

		try {

			IService<Car> carService = new CarServiceImpl();
			request.setAttribute("cars", carService.getAll());
			return new Router(ConfigurationManager.getProperty("path.page.goods"));

		} catch (ServiceLayerException e) {

			logger.log(Level.ERROR, "ServiceException in method execute " + e);
			request.setAttribute("nullPage", "Page not found. Business logic error.");
			return new Router(ConfigurationManager.getProperty("path.page.error"));
		}
	}
}
