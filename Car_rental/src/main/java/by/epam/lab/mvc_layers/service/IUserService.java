package by.epam.lab.mvc_layers.service;

import java.util.List;
import java.util.Optional;

import by.epam.lab.entity.User;
import by.epam.lab.entity.User.Role;
import by.epam.lab.exceptions.DAOException;

public interface IUserService extends IService<User> {

	@Override
	List<User> getAll() throws DAOException;

	@Override
	Optional<User> getEntityById(int id) throws DAOException;

	Optional<User> getUserByEmail(String email) throws DAOException;

	Optional<String> getPasswordByEmail(String email) throws DAOException;

	Optional<User> getUserByEmailPassword(String email, String password) throws DAOException;

	List<User> getUsersByRole(Role role) throws DAOException;

	boolean addUser(User user, String password) throws DAOException;

	boolean changeName(String name, int id) throws DAOException;

	boolean changeSecondName(String surname, int id) throws DAOException;

	boolean changeEmail(String email, int id) throws DAOException;

	boolean changePhone(String phone, int id) throws DAOException;

	boolean changePassword(String password, int id) throws DAOException;

	boolean changePassportData(String passportNumber, String passportIdentificationNumberr, int id) throws DAOException;

	@Override
	boolean delete(int id) throws DAOException;
}
