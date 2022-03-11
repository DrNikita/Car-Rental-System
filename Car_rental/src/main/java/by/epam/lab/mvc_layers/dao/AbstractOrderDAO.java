package by.epam.lab.mvc_layers.dao;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

import by.epam.lab.entity.Order;
import by.epam.lab.exceptions.DAOException;

public abstract class AbstractOrderDAO extends AbstractDAO<Order> {

	public AbstractOrderDAO(Connection connection) {
		super(connection);
	}

	public abstract List<Order> getOrdersOfUser(int id) throws DAOException;

	public abstract List<Order> getOrdersForCar(int id) throws DAOException;

	public abstract List<Order> getPaidOrders() throws DAOException;

	public abstract List<Order> getUnpaidOrders() throws DAOException;

	public abstract List<Order> getNotConsidered() throws DAOException;

	public abstract boolean confirmOrder(int id) throws DAOException;

	public abstract boolean rejectOrder(int id, String reason) throws DAOException;

	public abstract boolean changeStartDate(Date date, int id) throws DAOException;

	public abstract boolean changeEndDate(Date date, int id) throws DAOException;

	public abstract boolean changeConfirmationStatus(int status, int orderId) throws DAOException;

	public abstract boolean changeIsPaidStatus(boolean status, int orderId) throws DAOException;

	public abstract boolean changeCar(int carId, int orderId) throws DAOException;

	public abstract boolean changePrice(int price, int orderId) throws DAOException;

	public abstract boolean addOrder(Order order) throws DAOException;
}
