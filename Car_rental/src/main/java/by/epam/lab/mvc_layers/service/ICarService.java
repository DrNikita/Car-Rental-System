package by.epam.lab.mvc_layers.service;

import java.util.List;
import java.util.Optional;

import by.epam.lab.entity.Car;
import by.epam.lab.exceptions.DAOException;

public interface ICarService extends IService<Car> {

	@Override
	Optional<Car> getEntityById(int id) throws DAOException;

	@Override
	List<Car> getAll() throws DAOException;

	boolean addCar(Car car) throws DAOException;

	boolean changeCarPrice(int price, int id) throws DAOException;

	boolean changeCarImage(String link, int id) throws DAOException;

	String getBrand(int id) throws DAOException;

	String getModel(int id) throws DAOException;

	@Override
	boolean delete(int id) throws DAOException;
}
