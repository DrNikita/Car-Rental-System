package by.epam.lab.mvc_layers.dao.impl;

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
//import org.apache.logge

import by.epam.lab.entity.Car;
import by.epam.lab.entity.User;
import by.epam.lab.entity.User.Role;
import by.epam.lab.exceptions.DAOException;
import by.epam.lab.mvc_layers.connectionpool.ConnectionPool;
import by.epam.lab.mvc_layers.dao.UserDAO;
import by.epam.lab.utils.Encode;
import by.epam.lab.utils.Constants;

public class UserDAOImpl implements UserDAO {
	private static final Logger logger = LogManager.getLogger();
	private static final String SQL_FIND_ALL_USERS = "SELECT user_id,name,surname,email,phone,role,passport_number,passport_identification_number FROM users";
	private static final String SQL_FIND_USERS_BY_NAME = "SELECT user_id,name,surname,email,phone,role,passport_number,passport_identification_number FROM users WHERE name=?";
	private static final String SQL_FIND_USERS_BY_SURNAME = "SELECT user_id,name,surname,email,phone,role FROM users WHERE surname=?";
	private static final String SQL_FIND_USERS_BY_ROLE = "SELECT user_id,name,surname,email,phone,role,passport_number,passport_identification_number FROM users WHERE role=?";
	private static final String SQL_FIND_PASSWORD_BY_EMAIL = "SELECT password FROM users WHERE email=?";
	private static final String SQL_FIND_USER_BY_EMAIL = "SELECT user_id,name,surname,email,phone,role,passport_number,passport_identification_number FROM users WHERE email=?";
	private static final String SQL_ADD_USER = "INSERT INTO users (name,surname,email,phone,role,password,passport_number,passport_identification_number) values(?,?,?,?,?,?,?,?)";
	private static final String SQL_FIND_USER_BY_ID = "SELECT user_id,name,surname,email,phone,role,passport_number,passport_identification_number FROM cars WHERE car_id=?";
	private ConnectionPool connectionPool = ConnectionPool.getInstance();

	@Override
	public Optional<User> getUserById(int id) throws DAOException {
		logger.log(Level.INFO, "Find car by id = " + id);

		Optional<User> user = null;

		try (Connection connection = connectionPool.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_FIND_USER_BY_ID)) {

			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				user = Optional.of(createUser(resultSet));
			}

		} catch (SQLException e) {
			throw new DAOException("Dao exception", e);
		}

		return user;
	}

	@Override
	public List<User> getAllUsers() throws DAOException {
		logger.log(Level.INFO, "Find all users");

		List<User> users = new ArrayList<User>();

		try (Connection connection = connectionPool.getConnection();

				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(SQL_FIND_ALL_USERS)) {

			while (resultSet.next()) {
				users.add(createUser(resultSet));
			}

		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQLException in findAll: " + e.getMessage() + " : " + e.getErrorCode());
			throw new DAOException("Dao exception", e);
		}

		return users;
	}

	@Override
	public List<User> getUsersByName(String userName) throws DAOException {

		logger.log(Level.INFO, "Find user by name, name=  " + userName);
		List<User> users = new ArrayList<User>();

		try (Connection connection = connectionPool.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_FIND_USERS_BY_NAME)) {

			statement.setString(1, userName);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				users.add(createUser(resultSet));
			}

		} catch (SQLException e) {
			throw new DAOException("Dao exception", e);
		}

		return users;
	}

	@Override
	public List<User> getUsersBySurname(String userSurname) throws DAOException {

		logger.log(Level.INFO, "Find user by surname, surname=  " + userSurname);
		List<User> users = new ArrayList<User>();

		try (Connection connection = connectionPool.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_FIND_USERS_BY_SURNAME)) {

			statement.setString(1, userSurname);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				users.add(createUser(resultSet));
			}

		} catch (SQLException e) {
			throw new DAOException("Dao exception", e);
		}

		return users;
	}

	@Override
	public List<User> getUsersByRole(Role role) throws DAOException {

		logger.log(Level.INFO, "Find user by role, role=  " + role);
		List<User> users = new ArrayList<User>();

		try (Connection connection = connectionPool.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_FIND_USERS_BY_ROLE)) {

			statement.setString(1, role.name());
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				users.add(createUser(resultSet));
			}

		} catch (SQLException e) {
			throw new DAOException("Dao exception", e);
		}

		return users;
	}

	@Override
	public Optional<String> getPasswordByEmail(String email) throws DAOException {

		logger.log(Level.INFO, "Find password by email, email=  " + email);
		Optional<String> optionalPassword;
		String password = null;

		try (Connection connection = connectionPool.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_FIND_PASSWORD_BY_EMAIL)) {
			logger.log(Level.DEBUG, "in try block");

			statement.setString(1, email);
			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				password = resultSet.getString(Constants.PASSWORD);
				optionalPassword = Optional.of(password);
				logger.log(Level.INFO, "password=" + password);

			} else {
				optionalPassword = Optional.empty();
			}

		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQLException in method findPasswordByLogin " + e.getMessage());
			throw new DAOException("Dao exception", e);
		}

		return optionalPassword;
	}

	@Override
	public Optional<User> getUserByEmail(String email) throws DAOException {

		logger.log(Level.INFO, "Find user by email, email=  " + email);
		Optional<User> optionalUser;

		try (Connection connection = connectionPool.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_FIND_USER_BY_EMAIL)) {

			logger.log(Level.DEBUG, "in try block, login");
			statement.setString(1, email);
			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				User user = createUser(resultSet);
				optionalUser = Optional.of(user);

			} else {
				logger.log(Level.INFO, "didn't find user with login:" + email);
				optionalUser = Optional.empty();
			}

		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQL EXCEPTION " + e.getMessage() + "-" + e.getErrorCode());
			throw new DAOException("Dao exception in method findUserByEmail", e);
		}

		return optionalUser;
	}

	@Override
	public void addUser(User user, String password) throws DAOException {

		logger.log(Level.INFO, "Try to add user in db" + user);

		try (Connection connection = connectionPool.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_ADD_USER)) {

			statement.setString(1, user.getName());
			statement.setString(2, user.getSurname());
			statement.setString(3, user.getEmail());
			statement.setString(4, user.getPhone());
			statement.setString(5, user.getRole().toString());
			statement.setString(6, Encode.encodePassword(password));
			statement.setString(7, user.getPassportNumber());
			statement.setString(8, user.getPassportIdentificationNumber());
			int rowCount = statement.executeUpdate();

		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQL EXCEPTION " + e.getMessage() + "-" + e.getErrorCode());
			throw new DAOException("Dao exception in method addUser, when we try to add user:" + user, e);
		}
	}

	@Override
	public void changeName(String name, int id) throws DAOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void changeSurname(String surname, int id) throws DAOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void changeEmail(String email, int id) throws DAOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void changePhone(String phone, int id) throws DAOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void changePassword(String password, int id) throws DAOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void changePassportData(String passportNumber, String passportIdentificationNumberr, int id)
			throws DAOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteUser(int id) throws DAOException {
		// TODO Auto-generated method stub

	}

	private User createUser(ResultSet resultSet) throws SQLException {

		int userId = resultSet.getInt(Constants.USER_ID);
		String name = resultSet.getString(Constants.USER_NAME);
		String surname = resultSet.getString(Constants.USER_SURNAME);
		String email = resultSet.getString(Constants.USER_EMAIL);
		String phone = resultSet.getString(Constants.USER_PHONE);
		Role role = Role.valueOf(resultSet.getString(Constants.ROLE).toUpperCase());
		String passportNumber = resultSet.getString(Constants.USER_PASSPORT_NUMBER);
		String passportIdentificationNumber = resultSet.getString(Constants.USER_PASSPORT_IDENTIFICATION_NUMBER);

		User user = new User.Builder().setUserID(userId).setName(name).setSurname(surname).setEmail(email)
				.setPhone(phone).setRole(role).setPassportNumber(passportNumber)
				.setPassportIdentificationNumber(passportIdentificationNumber).build();

		logger.log(Level.INFO, "Found user id:" + userId + "::FIO: " + name + " " + surname);
		return user;
	}
}
