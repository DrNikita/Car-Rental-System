package by.epam.lab.command.manager;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.lab.command.ActionCommand;
import by.epam.lab.command.router.ErrorRouter;
import by.epam.lab.command.router.ForwardRouter;
import by.epam.lab.command.router.Router;
import by.epam.lab.exceptions.ServiceLayerException;
import by.epam.lab.mvc.service.IOrderService;
import by.epam.lab.mvc.service.impl.OrderServiceImpl;
import by.epam.lab.property_manager.ConfigurationManager;
import by.epam.lab.property_manager.EntityesManager;

public class ManagerMainCommand implements ActionCommand {

	private static final Logger logger = LogManager.getLogger();

	@Override
	public Router execute(HttpServletRequest request) {

		try {
			IOrderService orderService = new OrderServiceImpl();
			request.setAttribute(EntityesManager.getProperty("orders"), orderService.getAll());
			return new ForwardRouter(ConfigurationManager.getProperty("path.page.manager_main"));

		} catch (ServiceLayerException e) {

			logger.log(Level.ERROR, "SesrviceException in method execute " + e);
			return new ErrorRouter(e);
		}
	}
}
