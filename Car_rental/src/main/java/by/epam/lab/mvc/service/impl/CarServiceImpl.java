package by.epam.lab.mvc.service.impl;

import java.util.List;
import java.util.Optional;

import by.epam.lab.entity.Brand;
import by.epam.lab.entity.Car;
import by.epam.lab.exceptions.DAOException;
import by.epam.lab.exceptions.ServiceLayerException;
import by.epam.lab.mvc.connectionpool.WrapperConnector;
import by.epam.lab.mvc.dao.AbstractDAO;
import by.epam.lab.mvc.dao.impl.CarDAOImpl;
import by.epam.lab.mvc.service.ICarService;

public class CarServiceImpl implements ICarService {

	@Override
	public Optional<Car> getEntityById(int id) throws ServiceLayerException {

		try (WrapperConnector connector = new WrapperConnector();
				AbstractDAO<Car> carDao = new CarDAOImpl(connector.getConnection())) {

			return carDao.getEntityById(id);

		} catch (DAOException e) {
			throw new ServiceLayerException(e);
		}
	}

	@Override
	public List<Car> getAll() throws ServiceLayerException {

		try (WrapperConnector connector = new WrapperConnector();
				AbstractDAO<Car> carDao = new CarDAOImpl(connector.getConnection())) {

			return carDao.getAll();

		} catch (DAOException e) {
			throw new ServiceLayerException(e);
		}
	}

	@Override
	public boolean addCar(Car car) throws ServiceLayerException {

		try (WrapperConnector connector = new WrapperConnector();
				CarDAOImpl carDao = new CarDAOImpl(connector.getConnection())) {

			return carDao.addCar(car);

		} catch (DAOException e) {
			throw new ServiceLayerException(e);
		}
	}

	@Override
	public boolean changeBrand(Brand brand, int id) throws ServiceLayerException {

		try (WrapperConnector connector = new WrapperConnector();
				CarDAOImpl carDao = new CarDAOImpl(connector.getConnection())) {

			return carDao.changeBrand(brand, id);

		} catch (DAOException e) {
			throw new ServiceLayerException(e);
		}
	}

	@Override
	public boolean changeYearOfIssue(int year, int id) throws ServiceLayerException {

		try (WrapperConnector connector = new WrapperConnector();
				CarDAOImpl carDao = new CarDAOImpl(connector.getConnection())) {

			return carDao.changeYearOfIssue(year, id);

		} catch (DAOException e) {
			throw new ServiceLayerException(e);
		}
	}

	@Override
	public boolean changeCarPrice(int price, int id) throws ServiceLayerException {

		try (WrapperConnector connector = new WrapperConnector();
				CarDAOImpl carDao = new CarDAOImpl(connector.getConnection())) {

			return carDao.changeCarPrice(price, id);

		} catch (DAOException e) {
			throw new ServiceLayerException(e);
		}
	}

	@Override
	public boolean changeCarImage(String link, int id) throws ServiceLayerException {

		try (WrapperConnector connector = new WrapperConnector();
				CarDAOImpl carDao = new CarDAOImpl(connector.getConnection())) {

			return carDao.changeCarImage(link, id);

		} catch (DAOException e) {
			throw new ServiceLayerException(e);
		}
	}

	@Override
	public boolean delete(int id) throws ServiceLayerException {

		try (WrapperConnector connector = new WrapperConnector();
				CarDAOImpl carDao = new CarDAOImpl(connector.getConnection())) {

			return carDao.delete(id);

		} catch (DAOException e) {
			throw new ServiceLayerException(e);
		}
	}
}
