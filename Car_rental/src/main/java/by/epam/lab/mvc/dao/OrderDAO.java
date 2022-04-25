package by.epam.lab.mvc.dao;

import java.util.Date;
import java.util.List;

import by.epam.lab.entity.Order;
import by.epam.lab.exceptions.DAOException;

public interface OrderDAO {

	public List<Order> getOrdersOfUser(int id) throws DAOException;

	public List<Order> getOrdersForCar(int id) throws DAOException;

	public List<Order> getPaidOrders() throws DAOException;

	public List<Order> getUnpaidOrders() throws DAOException;

	public List<Order> getNotConsidered() throws DAOException;

	public boolean confirmOrder(int id) throws DAOException;

	public boolean rejectOrder(int id, String reason) throws DAOException;

	public boolean payOrder(int id) throws DAOException;

	public boolean changeStartDate(Date date, int id) throws DAOException;

	public boolean changeEndDate(Date date, int id) throws DAOException;

	public boolean changeConfirmationStatus(int status, int orderId) throws DAOException;

	public boolean changeIsPaidStatus(boolean status, int orderId) throws DAOException;

	public boolean changeCar(int carId, int orderId) throws DAOException;

	public boolean changePrice(int price, int orderId) throws DAOException;
}
