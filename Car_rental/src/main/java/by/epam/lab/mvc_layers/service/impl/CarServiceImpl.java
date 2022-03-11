package by.epam.lab.mvc_layers.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import by.epam.lab.entity.Car;
import by.epam.lab.exceptions.DAOException;
import by.epam.lab.exceptions.ServiceLayerException;
import by.epam.lab.mvc_layers.connectionpool.ConnectionPool;
import by.epam.lab.mvc_layers.dao.AbstractDAO;
import by.epam.lab.mvc_layers.dao.impl.CarDAOImpl;
import by.epam.lab.mvc_layers.service.ICarService;

public class CarServiceImpl implements ICarService {

	@Override
	public Optional<Car> getEntityById(int id) throws ServiceLayerException {
		try {

			ConnectionPool connectionPool = ConnectionPool.getInstance();
			Connection conn = connectionPool.getConnection();

			conn.setAutoCommit(false);

			AbstractDAO<Car> carDao = new CarDAOImpl(conn);
			Optional<Car> car = carDao.getEntityById(id);

			conn.commit();

			return car;

		} catch (SQLException | DAOException e) {
			throw new ServiceLayerException(e);
		}
	}

	@Override
	public List<Car> getAll() throws ServiceLayerException {

		try {

			ConnectionPool connectionPool = ConnectionPool.getInstance();
			Connection conn = connectionPool.getConnection();

			List<Car> cars = new ArrayList<>();

			conn.setAutoCommit(false);

			AbstractDAO<Car> carDao = new CarDAOImpl(conn);
			cars = carDao.getAll();

			conn.commit();

			return cars;

		} catch (SQLException | DAOException e) {
			throw new ServiceLayerException(e);
		}
	}

	@Override
	public boolean addCar(Car car) throws ServiceLayerException {
		try {

			ConnectionPool connectionPool = ConnectionPool.getInstance();
			Connection conn = connectionPool.getConnection();

			conn.setAutoCommit(false);

			AbstractDAO<Car> carDao = new CarDAOImpl(conn);
			((CarDAOImpl) carDao).addCar(car);

			conn.commit();

			return true;

		} catch (SQLException | DAOException e) {
			throw new ServiceLayerException(e);
		}
	}

	@Override
	public boolean changeCarPrice(int price, int id) throws ServiceLayerException {
		try {

			ConnectionPool connectionPool = ConnectionPool.getInstance();
			Connection conn = connectionPool.getConnection();

			conn.setAutoCommit(false);

			AbstractDAO<Car> carDao = new CarDAOImpl(conn);
			((CarDAOImpl) carDao).changeCarPrice(price, id);

			conn.commit();

			return true;

		} catch (SQLException | DAOException e) {
			throw new ServiceLayerException(e);
		}
	}

	@Override
	public boolean changeCarImage(String link, int id) throws ServiceLayerException {
		try {

			ConnectionPool connectionPool = ConnectionPool.getInstance();
			Connection conn = connectionPool.getConnection();

			conn.setAutoCommit(false);

			AbstractDAO<Car> carDao = new CarDAOImpl(conn);
			((CarDAOImpl) carDao).changeCarImage(link, id);

			conn.commit();

			return true;

		} catch (SQLException | DAOException e) {
			throw new ServiceLayerException(e);
		}
	}

	@Override
	public boolean delete(int id) throws ServiceLayerException {
		try {

			ConnectionPool connectionPool = ConnectionPool.getInstance();
			Connection conn = connectionPool.getConnection();

			conn.setAutoCommit(false);

			AbstractDAO<Car> carDao = new CarDAOImpl(conn);
			((CarDAOImpl) carDao).delete(id);

			conn.commit();

			return true;

		} catch (SQLException | DAOException e) {
			throw new ServiceLayerException(e);
		}
	}
}
