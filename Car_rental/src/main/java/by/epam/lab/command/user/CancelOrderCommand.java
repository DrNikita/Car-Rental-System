package by.epam.lab.command.user;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.lab.command.ActionCommand;
import by.epam.lab.controller.Router;
import by.epam.lab.exceptions.ServiceLayerException;
import by.epam.lab.mvc.service.IOrderService;
import by.epam.lab.mvc.service.impl.OrderServiceImpl;
import by.epam.lab.property_manager.ConfigurationManager;
import by.epam.lab.property_manager.EntityesManager;
import by.epam.lab.utils.ServletPaths;

public class CancelOrderCommand implements ActionCommand {

	private static final Logger logger = LogManager.getLogger();

	@Override
	public Router execute(HttpServletRequest request) {

		try {

			IOrderService orderService = new OrderServiceImpl();
			int orderId = Integer.parseInt(request.getParameter(EntityesManager.getProperty("order_id")));
			orderService.delete(orderId);
			return new Router(ServletPaths.MAIN);

		} catch (ServiceLayerException e) {
			logger.log(Level.ERROR, "Service exception in " + this.getClass().getName() + ": ", e);
			return new Router(ConfigurationManager.getProperty("path.page.error"));
		} catch (NumberFormatException e) {
			request.setAttribute("error", e);
			logger.log(Level.ERROR, "incorrect order id in " + this.getClass().getName() + ": ", e);
			return new Router(ConfigurationManager.getProperty("path.page.error"));
		}
	}
}
