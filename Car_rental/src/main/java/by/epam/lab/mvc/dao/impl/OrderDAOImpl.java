package by.epam.lab.mvc.dao.impl;

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
import by.epam.lab.mvc.dao.AbstractDAO;
import by.epam.lab.mvc.dao.OrderDAO;
import by.epam.lab.utils.DAOConstants;

public class OrderDAOImpl extends AbstractDAO<Order> implements OrderDAO {

	private static final Logger logger = LogManager.getLogger();

	private static final String SQL_FIND_ALL_ORDERS = "SELECT * FROM orders";
	private static final String SQL_FIND_ORDER_BY_ID = "SELECT id_order,user_id,car_id,start_date,end_date,price,is_paid,confirmation_status_id,rejection_reason FROM orders WHERE id_order=?";
	private static final String SQL_FIND_ORDERS_OF_USER = "SELECT id_order,user_id,car_id,start_date,end_date,price,is_paid,confirmation_status_id,rejection_reason FROM orders WHERE user_id=?";
	private static final String SQL_FIND_OEDER_FOR_CAR = "SELECT id_order,user_id,car_id,start_date,end_date,price,is_paid,confirmation_status_id,rejection_reason FROM orders WHERE car_id=?";
	private static final String SQL_FIND_PAID_ORDERS = "SELECT id_order,user_id,car_id,start_date,end_date,price,is_paid,confirmation_status_id,rejection_reason FROM orders WHERE is_paid=1";
	private static final String SQL_FIND_UNPAID_ORDERS = "SELECT id_order,user_id,car_id,start_date,end_date,price,is_paid,confirmation_status_id,rejection_reason FROM orders WHERE is_paid=0";
	private static final String SQL_FIND_NOT_CONSIDERED_ORDERS = "SELECT id_order,user_id,car_id,start_date,end_date,price,is_paid,confirmation_status_id,rejection_reason FROM orders WHERE confirmation_status_id=1";
	private static final String SQL_CONFIRM_ORDER = "UPDATE orders SET confirmation_status_id=2 WHERE id_order=?";
	private static final String SQL_REJECT_ORDER = "UPDATE orders SET confirmation_status_id=3,rejection_reason=? WHERE id_order=?";
	private static final String SQL_ADD_ORDER = "INSERT INTO orders (user_id,car_id,start_date,end_date,price,is_paid,confirmation_status_id,rejection_reason) values(?,?,?,?,?,?,?,?)";
	private static final String SQL_CHANGE_START_DATE = "UPDATE orders SET start_date=? WHERE id_order=?";
	private static final String SQL_CHANGE_END_DATE = "UPDATE orders SET end_date=? WHERE id_order=?";
	private static final String SQL_CHANGE_CONFIRMATION_STATUS = "UPDATE orders SET confirmation_status_id=? WHERE id_order=?";
	private static final String SQL_CHANGE_IS_PAID_STATUS = "UPDATE orders SET is_paid=? WHERE id_order=?";
	private static final String SQL_CHANGE_CAR = "UPDATE orders SET car_id=? WHERE id_order=?";
	private static final String SQL_CHANGE_PRICE = "UPDATE orders SET price=? WHERE id_order=?";
	private static final String SQL_DELETE_RDER = "delete from orders where id_order=?";

	public OrderDAOImpl(Connection connection) {
		super(connection);
	}

	@Override
	public List<Order> getAll() throws DAOException {

		try (Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(SQL_FIND_ALL_ORDERS)) {

			List<Order> orders = new ArrayList<>();

			while (resultSet.next()) {
				orders.add(create(resultSet));
			}

			return orders;

		} catch (SQLException e) {
			throw new DAOException();
		}
	}

	public List<Order> getOrdersOfUser(int id) throws DAOException {

		try (Statement statement = connection.createStatement();
				PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ORDERS_OF_USER)) {

			List<Order> orders = new ArrayList<>();

			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				orders.add(create(resultSet));
			}

			return orders;

		} catch (SQLException e) {
			throw new DAOException();
		}
	}

	public List<Order> getOrdersForCar(int id) throws DAOException {

		try (Statement statement = connection.createStatement();
				PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_OEDER_FOR_CAR)) {

			List<Order> orders = new ArrayList<>();

			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				orders.add(create(resultSet));
			}

			return orders;

		} catch (SQLException e) {
			throw new DAOException();
		}
	}

	@Override
	public List<Order> getPaidOrders() throws DAOException {

		try (Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(SQL_FIND_PAID_ORDERS)) {

			List<Order> orders = new ArrayList<>();

			while (resultSet.next()) {
				orders.add(create(resultSet));
			}

			return orders;

		} catch (SQLException e) {
			throw new DAOException();
		}
	}

	@Override
	public List<Order> getUnpaidOrders() throws DAOException {

		try (Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(SQL_FIND_UNPAID_ORDERS)) {

			List<Order> orders = new ArrayList<>();

			while (resultSet.next()) {
				orders.add(create(resultSet));
			}

			return orders;

		} catch (SQLException e) {
			throw new DAOException();
		}
	}

	@Override
	public List<Order> getNotConsidered() throws DAOException {

		try (Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(SQL_FIND_NOT_CONSIDERED_ORDERS)) {

			List<Order> orders = new ArrayList<>();

			while (resultSet.next()) {
				orders.add(create(resultSet));
			}

			return orders;

		} catch (SQLException e) {
			throw new DAOException();
		}
	}

	@Override
	public Optional<Order> getEntityById(int id) throws DAOException {
		logger.log(Level.INFO, "Find car by id = " + id);

		try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_ORDER_BY_ID)) {

			Optional<Order> order = Optional.empty();

			statement.setInt(1, id);

			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				order = Optional.of(create(resultSet));
			}

			return order;

		} catch (SQLException e) {
			throw new DAOException("Dao exception", e);
		}
	}

	@Override
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

			statement.setString(1, reason);
			statement.setInt(2, id);
			statement.executeUpdate();
			return true;

		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public boolean payOrder(int id) throws DAOException {

		try (PreparedStatement statement = connection.prepareStatement(SQL_CHANGE_IS_PAID_STATUS)) {

			statement.setBoolean(1, true);
			statement.setInt(2, id);
			statement.executeUpdate();
			return true;

		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	@Override
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

	@Override
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

	@Override
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

	@Override
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

	@Override
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

	@Override
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

	@Override
	public boolean addOrder(Order order) throws DAOException {

		try (PreparedStatement statement = connection.prepareStatement(SQL_ADD_ORDER)) {

			java.sql.Date sqlStatrtDate = new java.sql.Date(order.getStartDate().getTime());
			java.sql.Date sqlEndDate = new java.sql.Date(order.getEndDate().getTime());

			statement.setInt(1, order.getUser().getId());
			statement.setInt(2, order.getCar().getId());
			statement.setDate(3, sqlStatrtDate);
			statement.setDate(4, sqlEndDate);
			statement.setInt(5, order.getPrice());
			statement.setBoolean(6, order.getIsPaidStatus());
			statement.setInt(7, order.getConfirmationStatus().ordinal() + 1);
			statement.setString(8, order.getRejectionReason());
			statement.executeUpdate();

			return true;

		} catch (SQLException e) {
			throw new DAOException(e);
		}
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

		try (AbstractDAO<User> userDao = new UserDAOImpl(connection);
				AbstractDAO<Car> carDao = new CarDAOImpl(connection)) {

			int idOrder = resultSet.getInt(DAOConstants.ID_ORDER);

			int userId = resultSet.getInt(DAOConstants.USER_ID);
			User user = userDao.getEntityById(userId).get();

			int carId = resultSet.getInt(DAOConstants.CAR_ID);
			Car car = carDao.getEntityById(carId).get();

			Date startDate = resultSet.getDate(DAOConstants.ORDER_START_DATE);
			Date endDate = resultSet.getDate(DAOConstants.ORDER_END_DATE);
			int price = resultSet.getInt(DAOConstants.PRICE);
			boolean isPaid = resultSet.getBoolean(DAOConstants.IS_PAID_STATUS);
			ConfirmationStatus confirmationStatus = ConfirmationStatus
					.values()[resultSet.getInt(DAOConstants.ORDER_CONFIRMATION_STATUS_ID) - 1];

			String rejectionReason = resultSet.getString(DAOConstants.ORDER_REJECTION_REASON);

			Order order = new Order.Builder().setOrderId(idOrder).setUser(user).setCar(car).setStartDate(startDate)
					.setEndDate(endDate).setPrice(price).setIsPaidStatus(isPaid)
					.setConfirmationStatus(confirmationStatus).setRejectionReason(rejectionReason).build();

			return order;
		}
	}
}
