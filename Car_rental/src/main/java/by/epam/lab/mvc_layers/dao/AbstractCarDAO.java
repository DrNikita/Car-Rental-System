package by.epam.lab.mvc_layers.dao;

import java.sql.Connection;

import by.epam.lab.entity.Car;
import by.epam.lab.exceptions.DAOException;

public abstract class AbstractCarDAO extends AbstractDAO<Car> {

	public AbstractCarDAO(Connection connection) {
		super(connection);
	}

	public abstract boolean addCar(Car car) throws DAOException;

	public abstract boolean changeCarPrice(int price, int id) throws DAOException;

	public abstract boolean changeCarImage(String link, int id) throws DAOException;
}
