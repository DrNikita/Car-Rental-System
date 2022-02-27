package by.epam.lab.mvc_layers.dao;

import java.util.List;

import by.epam.lab.entity.Order;
import by.epam.lab.exceptions.DAOException;

public interface OrderDAO {

	List<Order> getAll() throws DAOException;

	List<Order> getByUserId(int id) throws DAOException;

	boolean confirmOrder(int id) throws DAOException;

	boolean rejectOrder(int id, String reason) throws DAOException;

	boolean deleteOrder(int id) throws DAOException;

	boolean pay(int id) throws DAOException;

	void returnCar(int id, int sum) throws DAOException;

	List<Order> getNewOrders() throws DAOException;

	List<Order> getPaidOrders() throws DAOException;

	List<Order> getUnPaidOrders() throws DAOException;

	List<Order> getDByUserId(int id) throws DAOException;

	void returnDamage(int id, String damage, int damageCost) throws DAOException;
}
