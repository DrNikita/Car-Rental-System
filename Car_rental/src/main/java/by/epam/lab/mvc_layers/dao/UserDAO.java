package by.epam.lab.mvc_layers.dao;

import java.util.List;
import java.util.Optional;

import by.epam.lab.entity.User;
import by.epam.lab.entity.User.Role;
import by.epam.lab.exceptions.DAOException;

public interface UserDAO {

	Optional<User> getUserById(int id) throws DAOException;

	List<User> getAllUsers() throws DAOException;

	List<User> getUsersByName(String userName) throws DAOException;

	List<User> getUsersBySurname(String userSurname) throws DAOException;

	Optional<User> getUserByEmail(String email) throws DAOException;

	List<User> getUsersByRole(Role role) throws DAOException;

	void addUser(User user, String password) throws DAOException;

	void changeName(String name, int id) throws DAOException;

	void changeSurname(String surname, int id) throws DAOException;

	void changeEmail(String email, int id) throws DAOException;

	void changePhone(String phone, int id) throws DAOException;

	void changePassword(String password, int id) throws DAOException;

	void changePassportData(String passportNumber, String passportIdentificationNumberr, int id) throws DAOException;

	Optional<String> getPasswordByEmail(String email) throws DAOException;

	void deleteUser(int id) throws DAOException;
}
