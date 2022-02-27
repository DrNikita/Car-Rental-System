package by.epam.lab.mvc_layers.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import by.epam.lab.entity.Order;
import by.epam.lab.exceptions.DAOException;
import by.epam.lab.mvc_layers.dao.OrderDAO;

public class OrderDAOImpl implements OrderDAO {

	@Override
	public List<Order> getAll() throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Order> getByUserId(int id) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean confirmOrder(int id) throws DAOException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean rejectOrder(int id, String reason) throws DAOException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteOrder(int id) throws DAOException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean pay(int id) throws DAOException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void returnCar(int id, int sum) throws DAOException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Order> getNewOrders() throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Order> getPaidOrders() throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Order> getUnPaidOrders() throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Order> getDByUserId(int id) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void returnDamage(int id, String damage, int damageCost) throws DAOException {
		// TODO Auto-generated method stub

	}

	private Order create(ResultSet resultSet) throws SQLException {
		return null;
	}
}
