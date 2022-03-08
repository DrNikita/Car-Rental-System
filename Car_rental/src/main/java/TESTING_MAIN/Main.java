package TESTING_MAIN;

import java.sql.Connection;
import java.sql.SQLException;
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
import by.epam.lab.entity.User;
import by.epam.lab.entity.User.Role;
import by.epam.lab.exceptions.DAOException;
import by.epam.lab.mvc_layers.connectionpool.ConnectionPool;
import by.epam.lab.mvc_layers.dao.impl.AbstractDAO;
import by.epam.lab.mvc_layers.dao.impl.CarDAO;
import by.epam.lab.mvc_layers.dao.impl.OrderDAO;
import by.epam.lab.mvc_layers.dao.impl.UserDAO;
import by.epam.lab.mvc_layers.service.IService;
import by.epam.lab.utils.Encode;

public class Main {
	public static void main(String[] args) throws DAOException, SQLException {
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
//	/	System.out.println(user.get());
//		Brand brand = new Brand(4, "q", "q");
//		Car car = new Car.Builder().setCarId(100).setBrand(brand).setYearOfIssue(3000).setPrice(55)
//				.setImageLink("image").build();
//		User user = new User.Builder().setName("Nikita").setSecondName("Drozd").setEmail("hell.lol.world@gmail.com")
//				.setPhone("+375(25)704-77-15").setRole(Role.MANAGER).setPassportNumber("MC1111111")
//				.setPassportIdentificationNumber("5002001J990SS6").build();
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
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection conn = connectionPool.getConnection();
		AbstractDAO<Car> dao = new CarDAO(conn);
		System.out.println(dao.getAll().toString());
		connectionPool.destroyPool();
	}
}
