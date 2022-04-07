package by.epam.lab.command.user;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.lab.command.ActionCommand;
import by.epam.lab.controller.Router;
import by.epam.lab.entity.User;
import by.epam.lab.exceptions.ServiceLayerException;
import by.epam.lab.mvc.service.IOrderService;
import by.epam.lab.mvc.service.impl.OrderServiceImpl;
import by.epam.lab.property_manager.ConfigurationManager;
import by.epam.lab.property_manager.EntityesManager;

public class UserOrdersCommand implements ActionCommand {

	private static final Logger logger = LogManager.getLogger();

	@Override
	public Router execute(HttpServletRequest request) {
		try {

			User user = (User) request.getSession().getAttribute(EntityesManager.getProperty("user"));
			IOrderService orderService = new OrderServiceImpl();
			request.setAttribute("orders", orderService.getOrdersOfUser(user.getId()));
			return new Router(ConfigurationManager.getProperty("path.page.userOrders"));

		} catch (ServiceLayerException e) {

			logger.log(Level.ERROR, "ServiceException in method execute " + e);
			request.setAttribute("nullPage", "Page not found. Business logic error.");
			return new Router(ConfigurationManager.getProperty("path.page.error"));
		}
	}
}
