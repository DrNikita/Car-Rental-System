package by.epam.lab.mvc_layers.service;

import java.util.List;
import java.util.Optional;

import by.epam.lab.entity.Entity;
import by.epam.lab.exceptions.DAOException;

public interface IService<T extends Entity> {

	List<T> getAll() throws DAOException;

	Optional<T> getEntityById(int id) throws DAOException;

	boolean delete(int id) throws DAOException;
}
