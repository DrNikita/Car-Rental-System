package by.epam.lab.mvc.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.lab.entity.Car;
import by.epam.lab.entity.Damage;
import by.epam.lab.entity.User;
import by.epam.lab.exceptions.DAOException;
import by.epam.lab.mvc.dao.AbstractDAO;
import by.epam.lab.mvc.dao.DamageDAO;
import by.epam.lab.utils.DAOConstants;

public class DamageDAOImpl extends AbstractDAO<Damage> implements DamageDAO {

	private static final Logger logger = LogManager.getLogger();

	private static final String SQL_GET_ALL_DAMAGE = "SELECT * FROM damage";
	private static final String SQL_GET_DAMAGE_BY_ID = "SELECT id_damage,user_id,car_id,description,price,is_paid FROM damage WHERE id_damage=?";
	private static final String SQL_DELETE_DAMAGE = "DELETE FROM damage WHERE id_damage=?";
	private static final String SQL_ADD_DAMAGE = "INSERT INTO damage (user_id,car_id,description,price,is_paid) values(?,?,?,?,?)";
	private static final String SQL_CHANGE_IS_PAID_STATUS = "UPDATE damage SET is_paid=? WHERE id_damage=?";

	public DamageDAOImpl(Connection connection) {
		super(connection);
	}

	@Override
	public List<Damage> getAll() throws DAOException {

		try (

				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(SQL_GET_ALL_DAMAGE)) {

			List<Damage> damage = new ArrayList<>();

			while (resultSet.next()) {
				damage.add(create(resultSet));
			}

			return damage;

		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQLException " + e.getMessage() + " : " + e.getErrorCode());
			throw new DAOException(e);
		}
	}

	@Override
	public Optional<Damage> getEntityById(int id) throws DAOException {

		try (PreparedStatement statement = connection.prepareStatement(SQL_GET_DAMAGE_BY_ID)) {

			Optional<Damage> order = Optional.empty();

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
	public boolean addDamage(Damage damage) throws DAOException {

		try (PreparedStatement statement = connection.prepareStatement(SQL_ADD_DAMAGE)) {

			statement.setInt(1, damage.getUser().getId());
			statement.setInt(2, damage.getCar().getId());
			statement.setString(3, damage.getDescription());
			statement.setInt(4, damage.getPrice());
			statement.setBoolean(5, damage.isPaid());
			statement.executeUpdate();

			return true;

		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public boolean payDamage(int id) throws DAOException {

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
	public boolean delete(int id) throws DAOException {

		try (PreparedStatement statement = connection.prepareStatement(SQL_DELETE_DAMAGE)) {

			statement.setInt(1, id);
			statement.executeUpdate();
			return true;

		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public Damage create(ResultSet resultSet) throws DAOException, SQLException {

		try (AbstractDAO<User> userDao = new UserDAOImpl(connection);
				AbstractDAO<Car> carDao = new CarDAOImpl(connection)) {

			int userId = resultSet.getInt(DAOConstants.USER_ID);
			int carId = resultSet.getInt(DAOConstants.CAR_ID);

			User user = userDao.getEntityById(userId).get();
			Car car = carDao.getEntityById(carId).get();

			String description = resultSet.getString(DAOConstants.DAMAGE_DESCRIPTION);
			int price = resultSet.getInt(DAOConstants.PRICE);
			boolean isPaid = resultSet.getBoolean(DAOConstants.IS_PAID_STATUS);

			Damage damage = new Damage.Builder().setUser(user).setCar(car).setDescriprion(description).setPrice(price)
					.setIsPaid(isPaid).build();

			return damage;
		}
	}
}
