package by.epam.lab.mvc_layers.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import by.epam.lab.entity.Order;
import by.epam.lab.exceptions.ServiceLayerException;

public interface IOrderService extends IService<Order> {

	@Override
	List<Order> getAll() throws ServiceLayerException;

	List<Order> getOrdersOfUser(int id) throws ServiceLayerException;

	List<Order> getOrdersForCar(int id) throws ServiceLayerException;

	List<Order> getPaidOrders() throws ServiceLayerException;

	List<Order> getUnpaidOrders() throws ServiceLayerException;

	List<Order> getNotConsidered() throws ServiceLayerException;

	@Override
	Optional<Order> getEntityById(int id) throws ServiceLayerException;

	boolean confirmOrder(int id) throws ServiceLayerException;

	boolean rejectOrder(int id, String reason) throws ServiceLayerException;

	boolean changeStartDate(Date date, int id) throws ServiceLayerException;

	boolean changeEndDate(Date date, int id) throws ServiceLayerException;

	boolean changeConfirmationStatus(int status, int orderId) throws ServiceLayerException;

	boolean changeIsPaidStatus(boolean status, int orderId) throws ServiceLayerException;

	boolean changeCar(int carId, int orderId) throws ServiceLayerException;

	boolean changePrice(int price, int orderId) throws ServiceLayerException;

	boolean addOrder(Order order) throws ServiceLayerException;

	@Override
	boolean delete(int id) throws ServiceLayerException;
}
