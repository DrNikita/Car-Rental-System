package by.epam.lab.mvc_layers.service;

import java.util.List;
import java.util.Optional;

import by.epam.lab.entity.Entity;
import by.epam.lab.exceptions.ServiceLayerException;

public interface IService<T extends Entity> {

	List<T> getAll() throws ServiceLayerException;

	Optional<T> getEntityById(int id) throws ServiceLayerException;

	boolean delete(int id) throws ServiceLayerException;
}
