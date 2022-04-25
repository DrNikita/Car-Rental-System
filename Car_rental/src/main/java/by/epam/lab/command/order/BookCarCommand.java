package by.epam.lab.command.order;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.lab.command.ActionCommand;
import by.epam.lab.command.router.ErrorRouter;
import by.epam.lab.command.router.ForwardRouter;
import by.epam.lab.command.router.Router;
import by.epam.lab.entity.Car;
import by.epam.lab.entity.Order;
import by.epam.lab.entity.Order.ConfirmationStatus;
import by.epam.lab.entity.User;
import by.epam.lab.exceptions.ServiceLayerException;
import by.epam.lab.mvc.service.IOrderService;
import by.epam.lab.mvc.service.impl.OrderServiceImpl;
import by.epam.lab.property_manager.ConfigurationManager;
import by.epam.lab.property_manager.EntityesManager;

public class BookCarCommand implements ActionCommand {

	private static final Logger logger = LogManager.getLogger();

	@Override
	public Router execute(HttpServletRequest request) {

		try {

			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date startDate = format.parse(request.getParameter(EntityesManager.getProperty("start_date")));
			Date endDate = format.parse(request.getParameter(EntityesManager.getProperty("end_date")));

			Car car = (Car) request.getSession().getAttribute(EntityesManager.getProperty("car"));
			User user = (User) request.getSession().getAttribute(EntityesManager.getProperty("user"));

			if (car != null && user != null && startDate != null && endDate != null) {

				Order order = new Order.Builder().setUser(user).setCar(car).setStartDate(startDate).setEndDate(endDate)
						.setIsPaidStatus(false).setConfirmationStatus(ConfirmationStatus.CONSIDERED).build();

				order.setPrice(order.getFullPrice());

				IOrderService orderService = new OrderServiceImpl();
				orderService.addOrder(order);

				return new ForwardRouter(ConfigurationManager.getProperty("path.command.main"));

			} else {
				logger.log(Level.INFO, "Car or user not in session.");
				return new ForwardRouter(ConfigurationManager.getProperty("path.command.main"));
			}

		} catch (ParseException e) {
			logger.log(Level.ERROR, e);
			return new ErrorRouter(e);

		} catch (ServiceLayerException e) {
			logger.log(Level.ERROR, e);
			return new ErrorRouter(e);
		}
	}
}
