package by.epam.lab.mvc_layers.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import by.epam.lab.entity.Order;
import by.epam.lab.exceptions.DAOException;

public interface IOrderService extends IService<Order> {

	@Override
	List<Order> getAll() throws DAOException;

	List<Order> getOrdersOfUser(int id) throws DAOException;

	List<Order> getOrdersForCar(int id) throws DAOException;

	List<Order> getPaidOrders() throws DAOException;

	List<Order> getUnpaidOrders() throws DAOException;

	List<Order> getNotConsidered() throws DAOException;

	@Override
	Optional<Order> getEntityById(int id) throws DAOException;

	boolean confirmOrder(int id) throws DAOException;

	boolean rejectOrder(int id, String reason) throws DAOException;

	boolean changeStartDate(Date date, int id) throws DAOException;

	boolean changeEndDate(Date date, int id) throws DAOException;

	boolean changeConfirmationStatus(int status, int orderId) throws DAOException;

	boolean changeIsPaidStatus(boolean status, int orderId) throws DAOException;

	boolean changeCar(int carId, int orderId) throws DAOException;

	boolean changePrice(int price, int orderId) throws DAOException;

	boolean addOrder(Order order) throws DAOException;

	@Override
	boolean delete(int id) throws DAOException;
}
