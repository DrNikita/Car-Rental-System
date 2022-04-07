package by.epam.lab.command.order;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.lab.command.ActionCommand;
import by.epam.lab.controller.Router;
import by.epam.lab.entity.Car;
import by.epam.lab.entity.Order;
import by.epam.lab.entity.Order.ConfirmationStatus;
import by.epam.lab.entity.User;
import by.epam.lab.exceptions.ServiceLayerException;
import by.epam.lab.mvc.service.IOrderService;
import by.epam.lab.mvc.service.impl.OrderServiceImpl;
import by.epam.lab.property_manager.EntityesManager;
import by.epam.lab.utils.ServletPaths;

public class BookCarCommand implements ActionCommand {

	private static final Logger logger = LogManager.getLogger();

	@Override
	public Router execute(HttpServletRequest request) {

		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

			Car car = (Car) request.getSession().getAttribute(EntityesManager.getProperty("car"));

			User user = (User) request.getSession().getAttribute(EntityesManager.getProperty("user"));

			Date startDate = format.parse(request.getParameter(EntityesManager.getProperty("start_date")));
			Date endDate = format.parse(request.getParameter(EntityesManager.getProperty("end_date")));

			if (car != null && user != null) {
				Order order = new Order.Builder().setUser(user).setCar(car).setStartDate(startDate).setEndDate(endDate)
						.setIsPaidStatus(false).setConfirmationStatus(ConfirmationStatus.CONSIDERED).build();

				order.setPrice(order.getFullPrice());

				IOrderService orderService = new OrderServiceImpl();
				orderService.addOrder(order);

				return new Router(ServletPaths.MAIN);

			} else {
				logger.log(Level.INFO, this.getClass().getName() + ": car or user not in session.");
				return new Router(ServletPaths.MAIN);
			}

		} catch (ParseException e) {
			logger.log(Level.INFO, this.getClass().getName() + ": incorrect dates.");
			return new Router(ServletPaths.LOGIN_PAGE);
		} catch (ServiceLayerException e) {
			logger.log(Level.INFO, this.getClass().getName() + ": " + e);
			return new Router(ServletPaths.LOGIN_PAGE);
		}
	}
}
