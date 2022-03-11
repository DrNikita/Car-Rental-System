package by.epam.lab.command;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import by.epam.lab.controller.Router;
import by.epam.lab.entity.Car;
import by.epam.lab.entity.Order;
import by.epam.lab.entity.Order.ConfirmationStatus;
import by.epam.lab.entity.User;
import by.epam.lab.exceptions.ServiceLayerException;
import by.epam.lab.mvc_layers.service.IService;
import by.epam.lab.mvc_layers.service.impl.CarServiceImpl;
import by.epam.lab.mvc_layers.service.impl.OrderServiceImpl;
import by.epam.lab.utils.EntityesManager;
import by.epam.lab.utils.ServletPaths;

public class BookCarCommand implements ActionCommand {

	@Override
	public Router execute(HttpServletRequest request) {

		Router router = new Router();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		try {
			IService<Car> carService = new CarServiceImpl();

			int carId = Integer
					.parseInt((String) request.getSession().getAttribute(EntityesManager.getProperty("car_id")));

			Car car = carService.getEntityById(carId).get();

			User user = (User) request.getSession().getAttribute(EntityesManager.getProperty("user"));

			Date startDate = format.parse(request.getParameter(EntityesManager.getProperty("start_date")));
			Date endDate = format.parse(request.getParameter(EntityesManager.getProperty("end_date")));

			Order order = new Order.Builder().setUser(user).setCar(car).setStartDate(startDate).setEndDate(endDate)
					.setIsPaidStatus(false).setConfirmationStatus(ConfirmationStatus.CONSIDERED).build();

			order.setPrice(order.getFullPrice());

			IService<Order> orderService = new OrderServiceImpl();
			((OrderServiceImpl) orderService).addOrder(order);

			router.setPagePath(ServletPaths.MAIN);

		} catch (ParseException e) {
			e.printStackTrace();

		} catch (ServiceLayerException e) {
			router.setPagePath(ServletPaths.LOGIN_PAGE);
		}

		return router;
	}
}
