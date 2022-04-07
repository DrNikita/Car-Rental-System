package by.epam.lab.mvc.dao;

import java.util.List;
import java.util.Optional;

import by.epam.lab.entity.User;
import by.epam.lab.entity.User.Role;
import by.epam.lab.exceptions.DAOException;

public interface UserDAO {

	public Optional<User> getUserByEmail(String email) throws DAOException;

	public Optional<String> getPasswordByEmail(String email) throws DAOException;

	public Optional<User> getUserByEmailPassword(String email, String password) throws DAOException;

	public List<User> getUsersByRole(Role role) throws DAOException;

	public boolean addUser(User user, String password) throws DAOException;

	public boolean changeName(String name, int id) throws DAOException;

	public boolean changeSecondName(String surname, int id) throws DAOException;

	public boolean changeEmail(String email, int id) throws DAOException;

	public boolean changePhone(String phone, int id) throws DAOException;

	public boolean changePassword(String password, int id) throws DAOException;

	public boolean changePassportData(String passportNumber, String passportIdentificationNumberr, int id)
			throws DAOException;
}
