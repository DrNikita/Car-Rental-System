package by.epam.lab.command.manager;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.lab.command.ActionCommand;
import by.epam.lab.controller.Router;
import by.epam.lab.entity.Order;
import by.epam.lab.entity.User;
import by.epam.lab.exceptions.ServiceLayerException;
import by.epam.lab.mvc.service.IOrderService;
import by.epam.lab.mvc.service.IUserService;
import by.epam.lab.mvc.service.impl.OrderServiceImpl;
import by.epam.lab.mvc.service.impl.UserServiceImpl;
import by.epam.lab.property_manager.ConfigurationManager;
import by.epam.lab.property_manager.EntityesManager;
import by.epam.lab.utils.ServletPaths;

public class OrderInformationCommand implements ActionCommand {

	private static final Logger logger = LogManager.getLogger();

	@Override
	public Router execute(HttpServletRequest request) {

		try {

			int orderId = Integer.parseInt(request.getParameter(EntityesManager.getProperty("orderId")));
			IOrderService orderService = new OrderServiceImpl();
			IUserService userService = new UserServiceImpl();
			Optional<Order> order = orderService.getEntityById(orderId);

			if (order.isPresent()) {

				Optional<User> user = userService.getEntityById(order.get().getUser().getId());

				if (user.isPresent()) {

					request.getSession().setAttribute(EntityesManager.getProperty("order"), order.get());
					request.setAttribute(EntityesManager.getProperty("user"), user.get());

					return new Router(ServletPaths.ORDER_INFORMATION_PAGE);
				}

				return new Router(ServletPaths.MANAGER_MAIN_PAGE);

			} else {
				return new Router(ServletPaths.MANAGER_MAIN_PAGE);
			}

		} catch (ServiceLayerException e) {
			logger.log(Level.ERROR, "ServiceException in method execute " + e);
			request.setAttribute("nullPage", "Page not found. Business logic error.");
			return new Router(ConfigurationManager.getProperty("path.page.error"));

		} catch (NumberFormatException e) {
			logger.log(Level.ERROR, "Incorrect order id: " + e);
			request.setAttribute("nullPage", "Page not found. Business logic error.");
			return new Router(ConfigurationManager.getProperty("path.page.error"));
		}
	}
}
