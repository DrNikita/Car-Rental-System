package by.epam.lab.mvc_layers.dao;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

import by.epam.lab.entity.Car;
import by.epam.lab.exceptions.DAOException;

public interface CarDAO {

	void addCar(Car car) throws DAOException;

	Optional<Car> getCarById(int id) throws DAOException;

	List<Car> getCarsByBrand(String brand) throws DAOException;

	List<Car> getCarsByModel(String model) throws DAOException;

	Optional<Car> getCarByBrandModel(String brand, String model) throws DAOException;

	List<Car> getAllCars() throws DAOException;

	void changeCarPrice(int price, int id) throws DAOException;

	void changeCarImage(String link, int id) throws DAOException;

	void changeDescriprion(String description, int id) throws DAOException;

	void deleteCar(int id) throws DAOException;
}
