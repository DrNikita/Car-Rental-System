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

import by.epam.lab.entity.User;
import by.epam.lab.entity.User.Role;
import by.epam.lab.exceptions.DAOException;
import by.epam.lab.mvc_layers.dao.AbstractUserDAO;
import by.epam.lab.utils.Encode;
import by.epam.lab.utils.DAOConstants;

public class UserDAOImpl extends AbstractUserDAO {

	private static final Logger logger = LogManager.getLogger();

	private static final String SQL_FIND_ALL_USERS = "SELECT id_user,role_id,name,second_name,email,phone,passport_number,passport_identification_number FROM users";
	private static final String SQL_FIND_USERS_BY_ROLE = "SELECT id_user,role_id,name,second_name,email,phone,passport_number,passport_identification_number FROM users WHERE role=?";
	private static final String SQL_FIND_PASSWORD_BY_EMAIL = "SELECT password FROM users WHERE email=?";
	private static final String SQL_FIND_USER_BY_EMAIL = "SELECT id_user,role_id,name,second_name,email,phone,passport_number,passport_identification_number FROM users WHERE email=?";
	private static final String SQL_ADD_USER = "INSERT INTO users (role_id,name,second_name,email,phone,password,passport_number,passport_identification_number) values(?,?,?,?,?,?,?,?)";
	private static final String SQL_FIND_USER_BY_ID = "SELECT id_user,role_id,name,second_name,email,phone,passport_number,passport_identification_number FROM users WHERE id_user=?";
	private static final String SQL_CHANGE_PASSPORT_DATA = "UPDATE users SET passport_number=?,passport_identification_number=? where id_user=?";
	private static final String SQL_FIND_USER_BY_EMAIL_PASSWORD = "SELECT id_user,role_id,name,second_name,email,phone,passport_number,passport_identification_number FROM users WHERE (email=?) AND (password=?)";

	public UserDAOImpl(Connection connection) {
		super(connection);
	}

	@Override
	public Optional<User> getEntityById(int id) throws DAOException {
		logger.log(Level.INFO, "Find car by id = " + id);

		try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_USER_BY_ID)) {

			Optional<User> user = Optional.empty();

			statement.setInt(1, id);

			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				user = Optional.of(create(resultSet));
			}

			return user;

		} catch (SQLException e) {
			throw new DAOException("Dao exception", e);
		}
	}

	@Override
	public List<User> getAll() throws DAOException {
		logger.log(Level.INFO, "Find all users");

		try (

				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(SQL_FIND_ALL_USERS)) {

			List<User> users = new ArrayList<User>();

			while (resultSet.next()) {
				users.add(create(resultSet));
			}

			return users;

		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQLException in findAll: " + e.getMessage() + " : " + e.getErrorCode());
			throw new DAOException("Dao exception", e);
		}
	}

	@Override
	public List<User> getUsersByRole(Role role) throws DAOException {

		logger.log(Level.INFO, "Find user by role, role=  " + role);

		try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_USERS_BY_ROLE)) {

			List<User> users = new ArrayList<User>();

			statement.setString(1, role.name());
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				users.add(create(resultSet));
			}

			return users;

		} catch (SQLException e) {
			throw new DAOException("Dao exception", e);
		}
	}

	@Override
	public Optional<String> getPasswordByEmail(String email) throws DAOException {

		logger.log(Level.INFO, "Find password by email, email=  " + email);

		try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_PASSWORD_BY_EMAIL)) {
			logger.log(Level.DEBUG, "in try block");

			Optional<String> optionalPassword = Optional.empty();

			statement.setString(1, email);
			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				String password = resultSet.getString(DAOConstants.USER_PASSWORD);
				optionalPassword = Optional.of(password);
				logger.log(Level.INFO, "password=" + password);

			}

			return optionalPassword;

		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQLException in method findPasswordByLogin " + e.getMessage());
			throw new DAOException("Dao exception", e);
		}
	}

	@Override
	public Optional<User> getUserByEmail(String email) throws DAOException {

		logger.log(Level.INFO, "Find user by email, email=  " + email);

		try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_USER_BY_EMAIL)) {

			Optional<User> optionalUser = Optional.empty();

			logger.log(Level.DEBUG, "in try block, login");
			statement.setString(1, email);
			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				User user = create(resultSet);
				optionalUser = Optional.of(user);

			} else {
				logger.log(Level.INFO, "didn't find user with login:" + email);
				optionalUser = Optional.empty();
			}

			return optionalUser;

		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQL EXCEPTION " + e.getMessage() + "-" + e.getErrorCode());
			throw new DAOException("Dao exception in method findUserByEmail", e);
		}
	}

	@Override
	public Optional<User> getUserByEmailPassword(String email, String password) throws DAOException {

		try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_USER_BY_EMAIL_PASSWORD)) {

			Optional<User> user = Optional.empty();

			statement.setString(1, email);
			statement.setString(2, Encode.encodePassword(password));
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				user = Optional.of(create(resultSet));
			}

			return user;

		} catch (SQLException e) {
			throw new DAOException();
		}
	}

	@Override
	public boolean addUser(User user, String password) throws DAOException {

		logger.log(Level.INFO, "Try to add user in db" + user);

		try (PreparedStatement statement = connection.prepareStatement(SQL_ADD_USER)) {

			statement.setInt(1, user.getRole().ordinal() + 1);
			statement.setString(2, user.getName());
			statement.setString(3, user.getSecondName());
			statement.setString(4, user.getEmail());
			statement.setString(5, user.getPhone());
			statement.setString(6, Encode.encodePassword(password));
			statement.setString(7, user.getPassportNumber());
			statement.setString(8, user.getPassportIdentificationNumber());
			statement.executeUpdate();
			return true;

		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQL EXCEPTION " + e.getMessage() + "-" + e.getErrorCode());
			throw new DAOException("Dao exception in method addUser, when we try to add user:" + user, e);
		}
	}

	@Override
	public boolean changeName(String name, int id) throws DAOException {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean changeSecondName(String surname, int id) throws DAOException {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean changeEmail(String email, int id) throws DAOException {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean changePhone(String phone, int id) throws DAOException {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean changePassword(String password, int id) throws DAOException {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean changePassportData(String passportNumber, String passportIdentificationNumberr, int id)
			throws DAOException {
		try (PreparedStatement statement = connection.prepareStatement(SQL_CHANGE_PASSPORT_DATA)) {

			statement.setString(1, passportNumber);
			statement.setString(2, passportIdentificationNumberr);
			statement.setInt(3, id);
			statement.executeUpdate();
			return true;

		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public boolean delete(int id) throws DAOException {
		// TODO Auto-generated method stub
		return true;
	}

	public User create(ResultSet resultSet) throws DAOException, SQLException {

		int userId = resultSet.getInt(DAOConstants.ID_USER);
		String name = resultSet.getString(DAOConstants.USER_NAME);
		String secondName = resultSet.getString(DAOConstants.USER_SECOND_NAME);
		String email = resultSet.getString(DAOConstants.USER_EMAIL);
		String phone = resultSet.getString(DAOConstants.USER_PHONE);
		Role role = Role.values()[(resultSet.getInt(DAOConstants.USER_ROLE)) - 1];
		String passportNumber = resultSet.getString(DAOConstants.USER_PASSPORT_NUMBER);
		String passportIdentificationNumber = resultSet.getString(DAOConstants.USER_PASSPORT_IDENTIFICATION_NUMBER);

		User user = new User.Builder().setUserID(userId).setName(name).setSecondName(secondName).setEmail(email)
				.setPhone(phone).setRole(role).setPassportNumber(passportNumber)
				.setPassportIdentificationNumber(passportIdentificationNumber).build();

		logger.log(Level.INFO, "Found user id:" + "::FIO: " + " ");

		return user;

	}
}
