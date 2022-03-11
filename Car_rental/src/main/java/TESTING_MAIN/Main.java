package TESTING_MAIN;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import by.epam.lab.entity.Brand;
import by.epam.lab.entity.Car;
import by.epam.lab.entity.Order;
import by.epam.lab.entity.Order.ConfirmationStatus;
import by.epam.lab.entity.User;
import by.epam.lab.entity.User.Role;
import by.epam.lab.exceptions.DAOException;
import by.epam.lab.exceptions.ServiceLayerException;
import by.epam.lab.mvc_layers.connectionpool.ConnectionPool;
import by.epam.lab.mvc_layers.dao.AbstractDAO;
import by.epam.lab.mvc_layers.dao.impl.CarDAOImpl;
import by.epam.lab.mvc_layers.dao.impl.OrderDAOImpl;
import by.epam.lab.mvc_layers.dao.impl.UserDAOImpl;
import by.epam.lab.mvc_layers.service.IService;
import by.epam.lab.mvc_layers.service.impl.CarServiceImpl;
import by.epam.lab.mvc_layers.service.impl.OrderServiceImpl;
import by.epam.lab.mvc_layers.service.impl.UserServiceImpl;
import by.epam.lab.utils.Encode;
import by.epam.lab.utils.EntityesManager;

public class Main {
	public static void main(String[] args) throws ServiceLayerException, ParseException {
//		OrderDAOImpl dao = new OrderDAOImpl();
//		System.out.println(dao.getEntityById(1));
//		Date date = new Date();
//		SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
//		dao.changeStartDate(date, 1);
		// dao.changeStartDate(date, 1);
//		UserDAOImpl u = new UserDAOImpl();
//		u.changePassportData(null, null, 3);
//		Optional<String> f = Optional.empty();
//		System.out.println(f);
//
//		Optional<User> user = u.getUserByEmail("nike.xrn@gmail.com");
//
//		u.addUser(user, "helloworld111");
//		CarDAOImpl carDaoImpl = new CarDAOImpl();
//		System.out.println(carDaoImpl.getAllCars().get(3));
//		carDaoImpl.getAllCarsFromCarPark();
//		UserDAOImpl userDao = new UserDAOImpl();
//		userDao.addUser(user, "kkk");
//		userDao.changePassportData("o", "o", 3);
//		carDaoImpl.changeDescriprion("So it's description for the car that was changed", 1);
//		carDaoImpl.changeCarPrice(90000, 1);
//		carDaoImpl.addCar(car);
//		carDaoImpl.deleteCar(2);
//		List<Car> cars = carDaoImpl.getAllFreeCars();
//		System.out.println(cars);
//		System.out.println(carDaoImpl.getCarById(1));

//		IService<Order> o = new OrderServiceImpl();
//		IService<User> u = new UserServiceImpl();
//		IService<Car> c = new CarServiceImpl();
//		Car car = c.getEntityById(1).get();
//		User user = u.getEntityById(3).get();
//		System.out.println(f.getAll() + "\n" + ((UserServiceImpl) f).getUserByEmail("hell.lol.world@gmail.com"));
//
//		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//		Date date = format.parse("2022-03-05");
//		Date dat = format.parse("2022-03-10");
//
//		Order order = new Order.Builder().setUser(user).setCar(car).setStartDate(date).setEndDate(dat)
//				.setIsPaidStatus(false).setConfirmationStatus(ConfirmationStatus.CONSIDERED).build();
//		order.setPrice(order.getFullPrice());
//
//		((OrderServiceImpl) o).addOrder(order);
		System.out.println(EntityesManager.getProperty("car"));
	}
}
