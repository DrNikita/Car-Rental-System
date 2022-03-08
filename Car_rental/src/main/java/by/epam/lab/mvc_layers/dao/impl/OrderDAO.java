package by.epam.lab.mvc_layers.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.lab.entity.Car;
import by.epam.lab.entity.Order;
import by.epam.lab.entity.User;
import by.epam.lab.entity.Order.ConfirmationStatus;
import by.epam.lab.exceptions.DAOException;
import by.epam.lab.utils.DAOConstants;

public class OrderDAO extends AbstractDAO<Order> {

	private static final Logger logger = LogManager.getLogger();

	private static final String SQL_FIND_ALL_ORDERS = "SELECT * FROM orders";
	private static final String SQL_FIND_ORDER_BY_ID = "SELECT id_order,user_id,car_id,start_date,end_date,price,is_paid,confirmation_status_id,rejection_reason FROM orders WHERE id_order=?";
	private static final String SQL_FIND_ORDERS_OF_USER = "SELECT id_order,user_id,car_id,start_date,end_date,price,is_paid,confirmation_status_id,rejection_reason FROM orders WHERE user_id=?";
	private static final String SQL_FIND_OEDER_FOR_CAR = "SELECT id_order,user_id,car_id,start_date,end_date,price,is_paid,confirmation_status_id,rejection_reason FROM orders WHERE car_id=?";
	private static final String SQL_FIND_PAID_ORDERS = "SELECT id_order,user_id,car_id,start_date,end_date,price,is_paid,confirmation_status_id,rejection_reason FROM orders WHERE is_paid=1";
	private static final String SQL_FIND_UNPAID_ORDERS = "SELECT id_order,user_id,car_id,start_date,end_date,price,is_paid,confirmation_status_id,rejection_reason FROM orders WHERE is_paid=0";
	private static final String SQL_FIND_NOT_CONSIDERED_ORDERS = "SELECT id_order,user_id,car_id,start_date,end_date,price,is_paid,confirmation_status_id,rejection_reason FROM orders WHERE confirmation_status_id=1";
	private static final String SQL_CONFIRM_ORDER = "UPDATE orders SET confirmation_status_id=2 WHERE id_order=?";
	private static final String SQL_REJECT_ORDER = "UPDATE orders SET confirmation_status_id=3 WHERE id_order=?";
	private static final String SQL_ADD_ORDER = "INSERT INTO orders (id_order,user_id,car_id,start_date,end_date,price,is_paid,confirmation_status_id,rejection_reason) values(?,?,?,?,?,?,?,?)";
	private static final String SQL_CHANGE_START_DATE = "UPDATE orders SET start_date=? WHERE id_order=?";
	private static final String SQL_CHANGE_END_DATE = "UPDATE orders SET end_date=? WHERE id_order=?";
	private static final String SQL_CHANGE_CONFIRMATION_STATUS = "UPDATE orders SET confirmation_status_id=? WHERE id_order=?";
	private static final String SQL_CHANGE_IS_PAID_STATUS = "UPDATE orders SET is_paid=? WHERE id_order=?";
	private static final String SQL_CHANGE_CAR = "UPDATE orders SET car_id=? WHERE id_order=?";
	private static final String SQL_CHANGE_PRICE = "UPDATE orders SET price=? WHERE id_order=?";
	private static final String SQL_DELETE_RDER = "delete from orders where id_order=?";

	public OrderDAO(Connection connection) {
		super(connection);
	}

	@Override
	public List<Order> getAll() throws DAOException {

		List<Order> orders = new ArrayList<>();

		try (

				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(SQL_FIND_ALL_ORDERS)) {

			while (resultSet.next()) {
				orders.add(create(resultSet));
			}

		} catch (SQLException e) {
			throw new DAOException();
		}
		return orders;
	}

	public List<Order> getOrdersOfUser(int id) throws DAOException {
		List<Order> orders = new ArrayList<>();

		try (

				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(SQL_FIND_ORDERS_OF_USER)) {

			while (resultSet.next()) {
				orders.add(create(resultSet));
			}

		} catch (SQLException e) {
			throw new DAOException();
		}
		return orders;
	}

	public List<Order> getOrdersForCar(int id) throws DAOException {
		List<Order> orders = new ArrayList<>();

		try (

				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(SQL_FIND_OEDER_FOR_CAR)) {

			while (resultSet.next()) {
				orders.add(create(resultSet));
			}

		} catch (SQLException e) {
			throw new DAOException();
		}
		return orders;
	}

	public List<Order> getPaidOrders() throws DAOException {
		List<Order> orders = new ArrayList<>();

		try (

				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(SQL_FIND_PAID_ORDERS)) {

			while (resultSet.next()) {
				orders.add(create(resultSet));
			}

		} catch (SQLException e) {
			throw new DAOException();
		}
		return orders;
	}

	public List<Order> getUnpaidOrders() throws DAOException {
		List<Order> orders = new ArrayList<>();

		try (

				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(SQL_FIND_UNPAID_ORDERS)) {

			while (resultSet.next()) {
				orders.add(create(resultSet));
			}

		} catch (SQLException e) {
			throw new DAOException();
		}
		return orders;
	}

	public List<Order> getNotConsidered() throws DAOException {
		List<Order> orders = new ArrayList<>();

		try (

				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(SQL_FIND_NOT_CONSIDERED_ORDERS)) {

			while (resultSet.next()) {
				orders.add(create(resultSet));
			}

		} catch (SQLException e) {
			throw new DAOException();
		}
		return orders;
	}

	@Override
	public Optional<Order> getEntityById(int id) throws DAOException {
		logger.log(Level.INFO, "Find car by id = " + id);

		Optional<Order> order = Optional.empty();

		try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_ORDER_BY_ID)) {

			statement.setInt(1, id);

			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				order = Optional.of(create(resultSet));
			}

		} catch (SQLException e) {
			throw new DAOException("Dao exception", e);
		}

		return order;
	}

	public boolean confirmOrder(int id) throws DAOException {
		try (PreparedStatement statement = connection.prepareStatement(SQL_CONFIRM_ORDER)) {

			statement.setInt(1, id);
			statement.executeUpdate();
			return true;

		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	public boolean rejectOrder(int id, String reason) throws DAOException {
		try (PreparedStatement statement = connection.prepareStatement(SQL_REJECT_ORDER)) {

			statement.setInt(1, id);
			statement.executeUpdate();
			return true;

		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	public boolean changeStartDate(Date date, int id) throws DAOException {

		try (PreparedStatement statement = connection.prepareStatement(SQL_CHANGE_START_DATE)) {

			java.sql.Date sqlDate = new java.sql.Date(date.getTime());

			statement.setDate(1, sqlDate);
			statement.setInt(2, id);
			statement.executeUpdate();
			return true;

		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	public boolean changeEndDate(Date date, int id) throws DAOException {

		try (PreparedStatement statement = connection.prepareStatement(SQL_CHANGE_END_DATE)) {

			java.sql.Date sqlDate = new java.sql.Date(date.getTime());

			statement.setDate(1, sqlDate);
			statement.setInt(2, id);
			statement.executeUpdate();
			return true;

		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	public boolean changeConfirmationStatus(int status, int id) throws DAOException {

		try (PreparedStatement statement = connection.prepareStatement(SQL_CHANGE_CONFIRMATION_STATUS)) {

			statement.setInt(1, status);
			statement.setInt(2, id);
			statement.executeUpdate();
			return true;

		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	public boolean changeIsPaidStatus(boolean status, int id) throws DAOException {
		try (PreparedStatement statement = connection.prepareStatement(SQL_CHANGE_IS_PAID_STATUS)) {

			statement.setBoolean(1, status);
			statement.setInt(2, id);
			statement.executeUpdate();
			return true;

		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	public boolean changeCar(int carId, int id) throws DAOException {
		try (PreparedStatement statement = connection.prepareStatement(SQL_CHANGE_CAR)) {

			statement.setInt(1, carId);
			statement.setInt(2, id);
			statement.executeUpdate();
			return true;

		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	public boolean changePrice(int price, int id) throws DAOException {

		try (PreparedStatement statement = connection.prepareStatement(SQL_CHANGE_PRICE)) {

			statement.setInt(1, price);
			statement.setInt(2, id);
			statement.executeUpdate();
			return true;

		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	public boolean addOrder(Order order) throws DAOException {

		try (PreparedStatement statement = connection.prepareStatement(SQL_ADD_ORDER)) {

			java.sql.Date sqlStatrtDate = new java.sql.Date(order.getStartDate().getTime());
			java.sql.Date sqlEndDate = new java.sql.Date(order.getEndDate().getTime());

			statement.setInt(1, order.getId());
			statement.setInt(2, order.getUser().getId());
			statement.setInt(3, order.getCar().getId());
			statement.setDate(4, sqlStatrtDate);
			statement.setDate(5, sqlEndDate);
			statement.setInt(6, order.getPrice());
			statement.setBoolean(7, order.getIsPaidStatus());
			statement.setInt(8, order.getConfirmationStatus().ordinal() + 1);
			statement.setString(9, order.getRejectionReason());

		} catch (SQLException e) {
			throw new DAOException();
		}

		return false;
	}

	@Override
	public boolean delete(int id) throws DAOException {
		try (PreparedStatement statement = connection.prepareStatement(SQL_DELETE_RDER)) {

			statement.setInt(1, id);
			statement.executeUpdate();
			return true;

		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public Order create(ResultSet resultSet) throws SQLException, DAOException {

		AbstractDAO<User> userDao = new UserDAO(connection);
		AbstractDAO<Car> carDao = new CarDAO(connection);

		int idOrder = resultSet.getInt(DAOConstants.ID_ORDER);

		int userId = resultSet.getInt(DAOConstants.ORDER_USER_ID);
		User user = userDao.getEntityById(userId).get();

		int carId = resultSet.getInt(DAOConstants.ORDER_CAR_ID);
		Car car = carDao.getEntityById(carId).get();

		Date startDate = resultSet.getDate(DAOConstants.ORDER_START_DATE);
		Date endDate = resultSet.getDate(DAOConstants.ORDER_END_DATE);
		int price = resultSet.getInt(DAOConstants.ORDER_PRICE);
		boolean isPaid = resultSet.getBoolean(DAOConstants.ORDER_IS_PAID_STATUS);
		ConfirmationStatus confirmationStatus = ConfirmationStatus
				.values()[resultSet.getInt(DAOConstants.ORDER_CONFIRMATION_STATUS_ID) - 1];

		String rejectionReason = resultSet.getString(DAOConstants.ORDER_REJECTION_REASON);

		Order order = new Order.Builder().setOrderId(idOrder).setUser(user).setCar(car).setStartDate(startDate)
				.setEndDate(endDate).setPrice(price).setIsPaidStatus(isPaid).setConfirmationStatus(confirmationStatus)
				.setRejectionReason(rejectionReason).build();

		return order;
	}
}
