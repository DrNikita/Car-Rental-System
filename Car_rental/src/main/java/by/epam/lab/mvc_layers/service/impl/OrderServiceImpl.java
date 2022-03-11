package by.epam.lab.mvc_layers.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import by.epam.lab.entity.Order;
import by.epam.lab.exceptions.DAOException;
import by.epam.lab.exceptions.ServiceLayerException;
import by.epam.lab.mvc_layers.connectionpool.ConnectionPool;
import by.epam.lab.mvc_layers.dao.AbstractDAO;
import by.epam.lab.mvc_layers.dao.impl.OrderDAOImpl;
import by.epam.lab.mvc_layers.service.IOrderService;

public class OrderServiceImpl implements IOrderService {

	@Override
	public List<Order> getAll() throws ServiceLayerException {

		try {

			ConnectionPool connectionPool = ConnectionPool.getInstance();
			Connection conn = connectionPool.getConnection();

			List<Order> orders = new ArrayList<>();

			conn.setAutoCommit(false);

			AbstractDAO<Order> orderDao = new OrderDAOImpl(conn);
			orders = orderDao.getAll();

			conn.commit();

			return orders;

		} catch (SQLException | DAOException e) {
			throw new ServiceLayerException(e);
		}
	}

	@Override
	public List<Order> getOrdersOfUser(int id) throws ServiceLayerException {

		try {

			ConnectionPool connectionPool = ConnectionPool.getInstance();
			Connection conn = connectionPool.getConnection();

			List<Order> orders = new ArrayList<>();

			conn.setAutoCommit(false);

			AbstractDAO<Order> orderDao = new OrderDAOImpl(conn);
			orders = ((OrderDAOImpl) orderDao).getOrdersOfUser(id);

			conn.commit();

			return orders;

		} catch (SQLException | DAOException e) {
			throw new ServiceLayerException(e);
		}
	}

	@Override
	public List<Order> getOrdersForCar(int id) throws ServiceLayerException {

		try {

			ConnectionPool connectionPool = ConnectionPool.getInstance();
			Connection conn = connectionPool.getConnection();

			List<Order> orders = new ArrayList<>();

			conn.setAutoCommit(false);

			AbstractDAO<Order> orderDao = new OrderDAOImpl(conn);
			orders = ((OrderDAOImpl) orderDao).getOrdersForCar(id);

			conn.commit();

			return orders;

		} catch (SQLException | DAOException e) {
			throw new ServiceLayerException(e);
		}
	}

	@Override
	public List<Order> getPaidOrders() throws ServiceLayerException {
		try {

			ConnectionPool connectionPool = ConnectionPool.getInstance();
			Connection conn = connectionPool.getConnection();

			List<Order> orders = new ArrayList<>();

			conn.setAutoCommit(false);

			AbstractDAO<Order> orderDao = new OrderDAOImpl(conn);
			orders = ((OrderDAOImpl) orderDao).getPaidOrders();

			conn.commit();

			return orders;

		} catch (SQLException | DAOException e) {
			throw new ServiceLayerException(e);
		}
	}

	@Override
	public List<Order> getUnpaidOrders() throws ServiceLayerException {
		try {

			ConnectionPool connectionPool = ConnectionPool.getInstance();
			Connection conn = connectionPool.getConnection();

			List<Order> orders = new ArrayList<>();

			conn.setAutoCommit(false);

			AbstractDAO<Order> orderDao = new OrderDAOImpl(conn);
			orders = ((OrderDAOImpl) orderDao).getUnpaidOrders();

			conn.commit();

			return orders;

		} catch (SQLException | DAOException e) {
			throw new ServiceLayerException(e);
		}
	}

	@Override
	public List<Order> getNotConsidered() throws ServiceLayerException {
		try {

			ConnectionPool connectionPool = ConnectionPool.getInstance();
			Connection conn = connectionPool.getConnection();

			List<Order> orders = new ArrayList<>();

			conn.setAutoCommit(false);

			AbstractDAO<Order> orderDao = new OrderDAOImpl(conn);
			orders = ((OrderDAOImpl) orderDao).getNotConsidered();

			conn.commit();

			return orders;

		} catch (SQLException | DAOException e) {
			throw new ServiceLayerException(e);
		}
	}

	@Override
	public Optional<Order> getEntityById(int id) throws ServiceLayerException {

		try {

			ConnectionPool connectionPool = ConnectionPool.getInstance();
			Connection conn = connectionPool.getConnection();

			conn.setAutoCommit(false);

			AbstractDAO<Order> orderDao = new OrderDAOImpl(conn);
			Optional<Order> order = orderDao.getEntityById(id);

			conn.commit();

			return order;

		} catch (SQLException | DAOException e) {
			throw new ServiceLayerException(e);
		}
	}

	@Override
	public boolean confirmOrder(int id) throws ServiceLayerException {

		try {

			ConnectionPool connectionPool = ConnectionPool.getInstance();
			Connection conn = connectionPool.getConnection();

			conn.setAutoCommit(false);

			AbstractDAO<Order> orderDao = new OrderDAOImpl(conn);
			((OrderDAOImpl) orderDao).confirmOrder(id);

			conn.commit();

			return true;

		} catch (SQLException | DAOException e) {
			throw new ServiceLayerException(e);
		}
	}

	@Override
	public boolean rejectOrder(int id, String reason) throws ServiceLayerException {
		try {

			ConnectionPool connectionPool = ConnectionPool.getInstance();
			Connection conn = connectionPool.getConnection();

			conn.setAutoCommit(false);

			AbstractDAO<Order> orderDao = new OrderDAOImpl(conn);
			((OrderDAOImpl) orderDao).rejectOrder(id, reason);

			conn.commit();

			return true;

		} catch (SQLException | DAOException e) {
			throw new ServiceLayerException(e);
		}
	}

	@Override
	public boolean changeStartDate(Date date, int id) throws ServiceLayerException {
		try {

			ConnectionPool connectionPool = ConnectionPool.getInstance();
			Connection conn = connectionPool.getConnection();

			conn.setAutoCommit(false);

			AbstractDAO<Order> orderDao = new OrderDAOImpl(conn);
			((OrderDAOImpl) orderDao).changeStartDate(date, id);

			conn.commit();

			return true;

		} catch (SQLException | DAOException e) {
			throw new ServiceLayerException(e);
		}
	}

	@Override
	public boolean changeEndDate(Date date, int id) throws ServiceLayerException {

		try {

			ConnectionPool connectionPool = ConnectionPool.getInstance();
			Connection conn = connectionPool.getConnection();

			conn.setAutoCommit(false);

			AbstractDAO<Order> orderDao = new OrderDAOImpl(conn);
			((OrderDAOImpl) orderDao).changeEndDate(date, id);

			conn.commit();

			return true;

		} catch (SQLException | DAOException e) {
			throw new ServiceLayerException(e);
		}
	}

	@Override
	public boolean changeConfirmationStatus(int status, int orderId) throws ServiceLayerException {
		try {

			ConnectionPool connectionPool = ConnectionPool.getInstance();
			Connection conn = connectionPool.getConnection();

			conn.setAutoCommit(false);

			AbstractDAO<Order> orderDao = new OrderDAOImpl(conn);
			((OrderDAOImpl) orderDao).changeConfirmationStatus(status, orderId);

			conn.commit();

			return true;

		} catch (SQLException | DAOException e) {
			throw new ServiceLayerException(e);
		}
	}

	@Override
	public boolean changeIsPaidStatus(boolean status, int orderId) throws ServiceLayerException {
		try {

			ConnectionPool connectionPool = ConnectionPool.getInstance();
			Connection conn = connectionPool.getConnection();

			conn.setAutoCommit(false);

			AbstractDAO<Order> orderDao = new OrderDAOImpl(conn);
			((OrderDAOImpl) orderDao).changeIsPaidStatus(status, orderId);

			conn.commit();

			return true;

		} catch (SQLException | DAOException e) {
			throw new ServiceLayerException(e);
		}
	}

	@Override
	public boolean changeCar(int carId, int orderId) throws ServiceLayerException {

		try {

			ConnectionPool connectionPool = ConnectionPool.getInstance();
			Connection conn = connectionPool.getConnection();

			conn.setAutoCommit(false);

			AbstractDAO<Order> orderDao = new OrderDAOImpl(conn);
			((OrderDAOImpl) orderDao).changeCar(carId, orderId);

			conn.commit();

			return true;

		} catch (SQLException | DAOException e) {
			throw new ServiceLayerException(e);
		}
	}

	@Override
	public boolean changePrice(int price, int orderId) throws ServiceLayerException {

		try {

			ConnectionPool connectionPool = ConnectionPool.getInstance();
			Connection conn = connectionPool.getConnection();

			conn.setAutoCommit(false);

			AbstractDAO<Order> orderDao = new OrderDAOImpl(conn);
			((OrderDAOImpl) orderDao).changePrice(price, orderId);

			conn.commit();

			return true;

		} catch (SQLException | DAOException e) {
			throw new ServiceLayerException(e);
		}
	}

	@Override
	public boolean addOrder(Order order) throws ServiceLayerException {

		try {

			ConnectionPool connectionPool = ConnectionPool.getInstance();
			Connection conn = connectionPool.getConnection();

			conn.setAutoCommit(false);

			AbstractDAO<Order> orderDao = new OrderDAOImpl(conn);
			((OrderDAOImpl) orderDao).addOrder(order);

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

			AbstractDAO<Order> orderDao = new OrderDAOImpl(conn);
			((OrderDAOImpl) orderDao).delete(id);

			conn.commit();

			return true;

		} catch (SQLException | DAOException e) {
			throw new ServiceLayerException(e);
		}
	}

}
