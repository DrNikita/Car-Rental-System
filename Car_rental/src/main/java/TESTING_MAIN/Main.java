package TESTING_MAIN;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import by.epam.lab.entity.Car;
import by.epam.lab.entity.User;
import by.epam.lab.exceptions.DAOException;
import by.epam.lab.mvc_layers.dao.impl.CarDAOImpl;
import by.epam.lab.mvc_layers.dao.impl.UserDAOImpl;
import by.epam.lab.utils.Encode;

public class Main {
	public static void main(String[] args) throws DAOException {
		UserDAOImpl u = new UserDAOImpl();
//
//		Optional<User> user = u.getUserByEmail("nike.xrn@gmail.com");
//
//	/	System.out.println(user.get());
		Car car = new Car.Builder().setBrand("Mersedes").setModel("AMG C43").setPrice(150000)
				.setImageLink("https://carexpert.ru/img/foto600/mercedes/mersc691.jpg").setDescription("It's a car")
				.build();
		CarDAOImpl carDaoImpl = new CarDAOImpl();
//		carDaoImpl.changeDescriprion("So it's description for the car that was changed", 1);
//		carDaoImpl.changeCarPrice(90000, 1);
//		carDaoImpl.addCar(car);
//		carDaoImpl.deleteCar(2);
//		List<Car> cars = carDaoImpl.getAllFreeCars();
//		System.out.println(cars);
//		System.out.println(carDaoImpl.getCarById(1));
	}
}
