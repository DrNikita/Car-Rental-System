package by.epam.lab.mvc_layers.service;

import java.util.List;
import java.util.Optional;

import by.epam.lab.entity.User;
import by.epam.lab.entity.User.Role;
import by.epam.lab.exceptions.ServiceLayerException;

public interface IUserService extends IService<User> {

	@Override
	List<User> getAll() throws ServiceLayerException;

	@Override
	Optional<User> getEntityById(int id) throws ServiceLayerException;

	Optional<User> getUserByEmail(String email) throws ServiceLayerException;

	Optional<String> getPasswordByEmail(String email) throws ServiceLayerException;

	Optional<User> getUserByEmailPassword(String email, String password) throws ServiceLayerException;

	List<User> getUsersByRole(Role role) throws ServiceLayerException;

	boolean addUser(User user, String password) throws ServiceLayerException;

	boolean changeName(String name, int id) throws ServiceLayerException;

	boolean changeSecondName(String surname, int id) throws ServiceLayerException;

	boolean changeEmail(String email, int id) throws ServiceLayerException;

	boolean changePhone(String phone, int id) throws ServiceLayerException;

	boolean changePassword(String password, int id) throws ServiceLayerException;

	boolean changePassportData(String passportNumber, String passportIdentificationNumberr, int id)
			throws ServiceLayerException;

	@Override
	boolean delete(int id) throws ServiceLayerException;
}
