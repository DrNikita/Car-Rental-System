package by.epam.lab.mvc_layers.dao;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

import by.epam.lab.entity.User;
import by.epam.lab.entity.User.Role;
import by.epam.lab.exceptions.DAOException;

public abstract class AbstractUserDAO extends AbstractDAO<User> {

	public AbstractUserDAO(Connection connection) {
		super(connection);
	}

	public abstract Optional<User> getUserByEmail(String email) throws DAOException;

	public abstract Optional<String> getPasswordByEmail(String email) throws DAOException;

	public abstract Optional<User> getUserByEmailPassword(String email, String password) throws DAOException;

	public abstract List<User> getUsersByRole(Role role) throws DAOException;

	public abstract boolean addUser(User user, String password) throws DAOException;

	public abstract boolean changeName(String name, int id) throws DAOException;

	public abstract boolean changeSecondName(String surname, int id) throws DAOException;

	public abstract boolean changeEmail(String email, int id) throws DAOException;

	public abstract boolean changePhone(String phone, int id) throws DAOException;

	public abstract boolean changePassword(String password, int id) throws DAOException;

	public abstract boolean changePassportData(String passportNumber, String passportIdentificationNumberr, int id)
			throws DAOException;
}
