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
import by.epam.lab.utils.ServletPaths;

public class AcceptOrderCommand implements ActionCommand {

	private static final Logger logger = LogManager.getLogger();

	@Override
	public Router execute(HttpServletRequest request) {

		try {

			Order order = (Order) request.getSession().getAttribute(EntityesManager.getProperty("order"));

			if (order != null) {

				IOrderService orderService = new OrderServiceImpl();
				orderService.confirmOrder(order.getId());
				logger.log(Level.INFO, this.getClass().getName() + ": Order: " + order.getId() + " was accepted.");
				return new Router(ServletPaths.MANAGER_MAIN);

			} else {
				logger.log(Level.INFO, this.getClass().getName() + ": order wosn't found.");
				return new Router(ServletPaths.MANAGER_MAIN);
			}

		} catch (ServiceLayerException e) {

			logger.log(Level.ERROR, "ServiceException in method execute " + e);
			request.setAttribute("nullPage", "Page not found. Business logic error.");
			return new Router(ConfigurationManager.getProperty("path.page.error"));

		}
	}
}
