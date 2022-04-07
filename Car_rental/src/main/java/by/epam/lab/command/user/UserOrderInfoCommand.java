package by.epam.lab.command.user;

import java.util.Optional;

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

public class UserOrderInfoCommand implements ActionCommand {

	private static final Logger logger = LogManager.getLogger();

	@Override
	public Router execute(HttpServletRequest request) {
		try {

			IOrderService orderService = new OrderServiceImpl();
			int orderId = Integer.parseInt(request.getParameter(EntityesManager.getProperty("order_id")));

			Optional<Order> order = orderService.getEntityById(orderId);

			if (order.isPresent()) {
				request.setAttribute(EntityesManager.getProperty("order"), order.get());
				return new Router(ConfigurationManager.getProperty("path.page.user_order_info"));

			} else {
				return new Router(ConfigurationManager.getProperty("path.page.main"));
			}

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
