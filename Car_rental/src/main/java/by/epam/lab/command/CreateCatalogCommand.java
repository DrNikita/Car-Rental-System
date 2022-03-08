package by.epam.lab.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.lab.controller.Router;
import by.epam.lab.entity.Car;
import by.epam.lab.exceptions.DAOException;
import by.epam.lab.mvc_layers.dao.impl.CarDAO;
import by.epam.lab.mvc_layers.service.IService;
import by.epam.lab.utils.ConfigurationManager;

public class CreateCatalogCommand implements ActionCommand {

	private static final Logger logger = LogManager.getLogger();

	@Override
	public Router execute(HttpServletRequest request) {
		Router router = new Router();
		try {
			IService<Car> carDao = new CarDAO();
			List<Car> cars = carDao.getAll();
			request.setAttribute("cars", cars);
			router = new Router(ConfigurationManager.getProperty("path.page.main"));
			return router;

		} catch (DAOException e) {
			logger.log(Level.INFO, "DAOException in " + this.getClass() + ": " + e);
			router = new Router(ConfigurationManager.getProperty("path.page.index"));
		}

		return router;
	}

}
