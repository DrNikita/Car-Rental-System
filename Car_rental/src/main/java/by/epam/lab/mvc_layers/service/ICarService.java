package by.epam.lab.mvc_layers.service;

import java.util.List;
import java.util.Optional;

import by.epam.lab.entity.Car;
import by.epam.lab.exceptions.ServiceLayerException;

public interface ICarService extends IService<Car> {

	@Override
	Optional<Car> getEntityById(int id) throws ServiceLayerException;

	@Override
	List<Car> getAll() throws ServiceLayerException;

	boolean addCar(Car car) throws ServiceLayerException;

	boolean changeCarPrice(int price, int id) throws ServiceLayerException;

	boolean changeCarImage(String link, int id) throws ServiceLayerException;

	@Override
	boolean delete(int id) throws ServiceLayerException;
}
