package by.epam.lab.command.user;

import javax.servlet.http.HttpServletRequest;

import by.epam.lab.command.ActionCommand;
import by.epam.lab.command.router.ErrorRouter;
import by.epam.lab.command.router.ForwardRouter;
import by.epam.lab.command.router.Router;
import by.epam.lab.entity.Order;
import by.epam.lab.exceptions.ServiceLayerException;
import by.epam.lab.mvc.service.impl.OrderServiceImpl;
import by.epam.lab.property_manager.ConfigurationManager;
import by.epam.lab.property_manager.EntityesManager;

public class PayOrderCommand implements ActionCommand {

	@Override
	public Router execute(HttpServletRequest request) {

		OrderServiceImpl orderService = new OrderServiceImpl();
		Order order = (Order) request.getSession().getAttribute(EntityesManager.getProperty("order"));

		try {
			orderService.changeIsPaidStatus(true, order.getId());
			return new ForwardRouter(ConfigurationManager.getProperty("path.page.main"));

		} catch (ServiceLayerException e) {
			return new ErrorRouter(e);
		}
	}
}
