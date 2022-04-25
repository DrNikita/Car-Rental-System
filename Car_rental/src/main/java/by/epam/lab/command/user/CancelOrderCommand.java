package by.epam.lab.command.user;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.lab.command.ActionCommand;
import by.epam.lab.command.router.ErrorRouter;
import by.epam.lab.command.router.ForwardRouter;
import by.epam.lab.command.router.Router;
import by.epam.lab.entity.Order;
import by.epam.lab.exceptions.ServiceLayerException;
import by.epam.lab.mvc.service.IOrderService;
import by.epam.lab.mvc.service.impl.OrderServiceImpl;
import by.epam.lab.property_manager.ConfigurationManager;
import by.epam.lab.property_manager.EntityesManager;

public class CancelOrderCommand implements ActionCommand {

	private static final Logger logger = LogManager.getLogger();

	@Override
	public Router execute(HttpServletRequest request) {

		try {

			Order order = (Order) request.getSession().getAttribute(EntityesManager.getProperty("order"));

			if (order != null) {
				IOrderService orderService = new OrderServiceImpl();
				orderService.delete(order.getId());
				return new ForwardRouter(ConfigurationManager.getProperty("path.command.main"));

			} else {
				logger.log(Level.ERROR, "User not in the session.");
				return new ForwardRouter();
			}

		} catch (ServiceLayerException e) {

			logger.log(Level.ERROR, "Service exception in " + this.getClass().getName() + ": ", e);
			return new ErrorRouter(e);
		}
	}
}
