package by.epam.lab.mvc.service.impl;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.lab.entity.User;
import by.epam.lab.entity.User.Role;
import by.epam.lab.exceptions.DAOException;
import by.epam.lab.exceptions.ServiceLayerException;
import by.epam.lab.mvc.connectionpool.WrapperConnector;
import by.epam.lab.mvc.dao.AbstractDAO;
import by.epam.lab.mvc.dao.impl.UserDAOImpl;
import by.epam.lab.mvc.service.IUserService;

public class UserServiceImpl implements IUserService {

	private static final Logger logger = LogManager.getLogger();

	@Override
	public List<User> getAll() throws ServiceLayerException {

		logger.log(Level.INFO, "getAll");

		try (WrapperConnector connector = new WrapperConnector();
				AbstractDAO<User> userDao = new UserDAOImpl(connector.getConnection());) {

			return userDao.getAll();

		} catch (DAOException e) {
			throw new ServiceLayerException(e);
		}
	}

	@Override
	public Optional<User> getEntityById(int id) throws ServiceLayerException {

		try (WrapperConnector connector = new WrapperConnector();
				AbstractDAO<User> userDao = new UserDAOImpl(connector.getConnection());) {

			return userDao.getEntityById(id);

		} catch (DAOException e) {
			throw new ServiceLayerException(e);
		}
	}

	@Override
	public Optional<User> getUserByEmail(String email) throws ServiceLayerException {

		try (WrapperConnector connector = new WrapperConnector();
				UserDAOImpl userDao = new UserDAOImpl(connector.getConnection())) {

			return userDao.getUserByEmail(email);

		} catch (DAOException e) {
			throw new ServiceLayerException(e);
		}
	}

	@Override
	public Optional<String> getPasswordByEmail(String email) throws ServiceLayerException {

		try (WrapperConnector connector = new WrapperConnector();
				UserDAOImpl userDao = new UserDAOImpl(connector.getConnection())) {

			return userDao.getPasswordByEmail(email);

		} catch (DAOException e) {
			throw new ServiceLayerException(e);
		}
	}

	@Override
	public Optional<User> getUserByEmailPassword(String email, String password) throws ServiceLayerException {

		try (WrapperConnector connector = new WrapperConnector();
				UserDAOImpl userDao = new UserDAOImpl(connector.getConnection())) {

			return userDao.getUserByEmailPassword(email, password);

		} catch (DAOException e) {
			throw new ServiceLayerException(e);
		}
	}

	@Override
	public List<User> getUsersByRole(Role role) throws ServiceLayerException {

		try (WrapperConnector connector = new WrapperConnector();
				UserDAOImpl userDao = new UserDAOImpl(connector.getConnection())) {

			return userDao.getUsersByRole(role);

		} catch (DAOException e) {
			throw new ServiceLayerException(e);
		}
	}

	@Override
	public boolean addUser(User user, String password) throws ServiceLayerException {

		try (WrapperConnector connector = new WrapperConnector();
				UserDAOImpl userDao = new UserDAOImpl(connector.getConnection())) {

			return userDao.addUser(user, password);

		} catch (DAOException e) {
			throw new ServiceLayerException(e);
		}
	}

	@Override
	public boolean changeName(String name, int id) throws ServiceLayerException {

		try (WrapperConnector connector = new WrapperConnector();
				UserDAOImpl userDao = new UserDAOImpl(connector.getConnection())) {

			return userDao.changeName(name, id);

		} catch (DAOException e) {
			throw new ServiceLayerException(e);
		}
	}

	@Override
	public boolean changeSecondName(String surname, int id) throws ServiceLayerException {

		try (WrapperConnector connector = new WrapperConnector();
				UserDAOImpl userDao = new UserDAOImpl(connector.getConnection())) {

			return userDao.changeSecondName(surname, id);

		} catch (DAOException e) {
			throw new ServiceLayerException(e);
		}
	}

	@Override
	public boolean changeEmail(String email, int id) throws ServiceLayerException {

		try (WrapperConnector connector = new WrapperConnector();
				UserDAOImpl userDao = new UserDAOImpl(connector.getConnection())) {

			return userDao.changeEmail(email, id);

		} catch (DAOException e) {
			throw new ServiceLayerException(e);
		}
	}

	@Override
	public boolean changePhone(String phone, int id) throws ServiceLayerException {

		try (WrapperConnector connector = new WrapperConnector();
				UserDAOImpl userDao = new UserDAOImpl(connector.getConnection())) {

			return userDao.changePhone(phone, id);

		} catch (DAOException e) {
			throw new ServiceLayerException(e);
		}
	}

	@Override
	public boolean changePassword(String password, int id) throws ServiceLayerException {

		try (WrapperConnector connector = new WrapperConnector();
				UserDAOImpl userDao = new UserDAOImpl(connector.getConnection())) {

			return userDao.changePassword(password, id);

		} catch (DAOException e) {
			throw new ServiceLayerException(e);
		}
	}

	@Override
	public boolean changePassportData(String passportNumber, String passportIdentificationNumberr, int id)
			throws ServiceLayerException {

		try (WrapperConnector connector = new WrapperConnector();
				UserDAOImpl userDao = new UserDAOImpl(connector.getConnection())) {

			return userDao.changePassportData(passportNumber, passportIdentificationNumberr, id);

		} catch (DAOException e) {
			throw new ServiceLayerException(e);
		}
	}

	@Override
	public boolean delete(int id) throws ServiceLayerException {

		try (WrapperConnector connector = new WrapperConnector();
				UserDAOImpl userDao = new UserDAOImpl(connector.getConnection())) {

			return userDao.delete(id);

		} catch (DAOException e) {
			throw new ServiceLayerException(e);
		}
	}
}
