package by.epam.lab.command.manager;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.lab.command.ActionCommand;
import by.epam.lab.controller.Router;
import by.epam.lab.entity.Order;
import by.epam.lab.exceptions.ServiceLayerException;
import by.epam.lab.mvc.service.IOrderService;
import by.epam.lab.mvc.service.impl.OrderServiceImpl;
import by.epam.lab.property_manager.ConfigurationManager;
import by.epam.lab.property_manager.EntityesManager;

public class ReturnCarCommand implements ActionCommand {

	private static final Logger logger = LogManager.getLogger();

	@Override
	public Router execute(HttpServletRequest request) {

		try {
			Order order = (Order) request.getSession().getAttribute(EntityesManager.getProperty("order"));
			IOrderService orderService = new OrderServiceImpl();

			if (order != null) {
				orderService.delete(order.getId());
				logger.log(Level.INFO, "Car " + order.getCar().getId() + " was returnd.");
				return new Router(ConfigurationManager.getProperty("path.page.to_manager_main"));

			} else {
				logger.log(Level.INFO, this.getClass().getName() + ": Order wasn't fount.");
				return new Router(ConfigurationManager.getProperty("path.page.to_manager_main"));
			}
		} catch (ServiceLayerException e) {
			logger.log(Level.ERROR, "ServiceException in method execute " + e);
			request.setAttribute("nullPage", "Page not found. Business logic error.");
			return new Router(ConfigurationManager.getProperty("path.page.error"));
		}
	}
}
