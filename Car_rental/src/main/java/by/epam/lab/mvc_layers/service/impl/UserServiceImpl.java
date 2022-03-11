package by.epam.lab.mvc_layers.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import by.epam.lab.entity.User;
import by.epam.lab.entity.User.Role;
import by.epam.lab.exceptions.DAOException;
import by.epam.lab.exceptions.ServiceLayerException;
import by.epam.lab.mvc_layers.connectionpool.ConnectionPool;
import by.epam.lab.mvc_layers.dao.AbstractDAO;
import by.epam.lab.mvc_layers.dao.impl.UserDAOImpl;
import by.epam.lab.mvc_layers.service.IUserService;

public class UserServiceImpl implements IUserService {

	@Override
	public List<User> getAll() throws ServiceLayerException {

		try {

			ConnectionPool connectionPool = ConnectionPool.getInstance();
			Connection conn = connectionPool.getConnection();

			List<User> users = new ArrayList<>();

			conn.setAutoCommit(false);

			AbstractDAO<User> userDao = new UserDAOImpl(conn);
			users = userDao.getAll();

			conn.commit();

			return users;

		} catch (SQLException | DAOException e) {
			throw new ServiceLayerException(e);
		}
	}

	@Override
	public Optional<User> getEntityById(int id) throws ServiceLayerException {
		try {

			ConnectionPool connectionPool = ConnectionPool.getInstance();
			Connection conn = connectionPool.getConnection();

			conn.setAutoCommit(false);

			AbstractDAO<User> userDao = new UserDAOImpl(conn);
			Optional<User> user = userDao.getEntityById(id);

			conn.commit();

			return user;

		} catch (SQLException | DAOException e) {
			throw new ServiceLayerException(e);
		}
	}

	@Override
	public Optional<User> getUserByEmail(String email) throws ServiceLayerException {
		try {

			ConnectionPool connectionPool = ConnectionPool.getInstance();
			Connection conn = connectionPool.getConnection();

			conn.setAutoCommit(false);

			AbstractDAO<User> userDao = new UserDAOImpl(conn);
			Optional<User> user = ((UserDAOImpl) userDao).getUserByEmail(email);

			conn.commit();

			return user;

		} catch (SQLException | DAOException e) {
			throw new ServiceLayerException(e);
		}
	}

	@Override
	public Optional<String> getPasswordByEmail(String email) throws ServiceLayerException {
		try {

			ConnectionPool connectionPool = ConnectionPool.getInstance();
			Connection conn = connectionPool.getConnection();

			conn.setAutoCommit(false);

			AbstractDAO<User> userDao = new UserDAOImpl(conn);
			Optional<String> password = ((UserDAOImpl) userDao).getPasswordByEmail(email);

			conn.commit();

			return password;

		} catch (SQLException | DAOException e) {
			throw new ServiceLayerException(e);
		}
	}

	@Override
	public Optional<User> getUserByEmailPassword(String email, String password) throws ServiceLayerException {

		try {

			ConnectionPool connectionPool = ConnectionPool.getInstance();
			Connection conn = connectionPool.getConnection();

			conn.setAutoCommit(false);

			AbstractDAO<User> userDao = new UserDAOImpl(conn);
			Optional<User> user = ((UserDAOImpl) userDao).getUserByEmailPassword(email, password);

			conn.commit();

			return user;

		} catch (SQLException | DAOException e) {
			throw new ServiceLayerException(e);
		}
	}

	@Override
	public List<User> getUsersByRole(Role role) throws ServiceLayerException {

		try {

			ConnectionPool connectionPool = ConnectionPool.getInstance();
			Connection conn = connectionPool.getConnection();

			List<User> users = new ArrayList<>();

			conn.setAutoCommit(false);

			AbstractDAO<User> userDao = new UserDAOImpl(conn);
			users = ((UserDAOImpl) userDao).getUsersByRole(role);

			conn.commit();

			return users;

		} catch (SQLException | DAOException e) {
			throw new ServiceLayerException(e);
		}
	}

	@Override
	public boolean addUser(User user, String password) throws ServiceLayerException {

		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection conn = connectionPool.getConnection();

		try {

			conn.setAutoCommit(false);

			AbstractDAO<User> userDao = new UserDAOImpl(conn);
			((UserDAOImpl) userDao).addUser(user, password);

			conn.commit();

			return true;

		} catch (SQLException | DAOException e) {
			throw new ServiceLayerException(e);
		}
	}

	@Override
	public boolean changeName(String name, int id) throws ServiceLayerException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection conn = connectionPool.getConnection();

		try {

			conn.setAutoCommit(false);

			AbstractDAO<User> userDao = new UserDAOImpl(conn);
			((UserDAOImpl) userDao).changeName(name, id);

			conn.commit();

			return true;

		} catch (SQLException | DAOException e) {
			throw new ServiceLayerException(e);
		}
	}

	@Override
	public boolean changeSecondName(String surname, int id) throws ServiceLayerException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection conn = connectionPool.getConnection();

		try {

			conn.setAutoCommit(false);

			AbstractDAO<User> userDao = new UserDAOImpl(conn);
			((UserDAOImpl) userDao).changeSecondName(surname, id);

			conn.commit();

			return true;

		} catch (SQLException | DAOException e) {
			throw new ServiceLayerException(e);
		}
	}

	@Override
	public boolean changeEmail(String email, int id) throws ServiceLayerException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection conn = connectionPool.getConnection();

		try {

			conn.setAutoCommit(false);

			AbstractDAO<User> userDao = new UserDAOImpl(conn);
			((UserDAOImpl) userDao).changeEmail(email, id);

			conn.commit();

			return true;

		} catch (SQLException | DAOException e) {
			throw new ServiceLayerException(e);
		}
	}

	@Override
	public boolean changePhone(String phone, int id) throws ServiceLayerException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection conn = connectionPool.getConnection();

		try {

			conn.setAutoCommit(false);

			AbstractDAO<User> userDao = new UserDAOImpl(conn);
			((UserDAOImpl) userDao).changePhone(phone, id);

			conn.commit();

			return true;

		} catch (SQLException | DAOException e) {
			throw new ServiceLayerException(e);
		}
	}

	@Override
	public boolean changePassword(String password, int id) throws ServiceLayerException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection conn = connectionPool.getConnection();

		try {

			conn.setAutoCommit(false);

			AbstractDAO<User> userDao = new UserDAOImpl(conn);
			((UserDAOImpl) userDao).changePassword(password, id);

			conn.commit();

			return true;

		} catch (SQLException | DAOException e) {
			throw new ServiceLayerException(e);
		}
	}

	@Override
	public boolean changePassportData(String passportNumber, String passportIdentificationNumberr, int id)
			throws ServiceLayerException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection conn = connectionPool.getConnection();

		try {

			conn.setAutoCommit(false);

			AbstractDAO<User> userDao = new UserDAOImpl(conn);
			((UserDAOImpl) userDao).changePassportData(passportNumber, passportIdentificationNumberr, id);

			conn.commit();

			return true;

		} catch (SQLException | DAOException e) {
			throw new ServiceLayerException(e);
		}
	}

	@Override
	public boolean delete(int id) throws ServiceLayerException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection conn = connectionPool.getConnection();

		try {

			conn.setAutoCommit(false);

			AbstractDAO<User> userDao = new UserDAOImpl(conn);
			((UserDAOImpl) userDao).delete(id);

			conn.commit();

			return true;

		} catch (SQLException | DAOException e) {
			throw new ServiceLayerException(e);
		}
	}

}
