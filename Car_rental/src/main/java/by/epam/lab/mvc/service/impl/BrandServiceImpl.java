package by.epam.lab.mvc.service.impl;

import java.util.List;
import java.util.Optional;

import by.epam.lab.entity.Brand;
import by.epam.lab.exceptions.DAOException;
import by.epam.lab.exceptions.ServiceLayerException;
import by.epam.lab.mvc.connectionpool.WrapperConnector;
import by.epam.lab.mvc.dao.AbstractDAO;
import by.epam.lab.mvc.dao.impl.BrandDAOImpl;
import by.epam.lab.mvc.service.IBrandService;

public class BrandServiceImpl implements IBrandService {

	@Override
	public List<Brand> getAll() throws ServiceLayerException {

		try (WrapperConnector connector = new WrapperConnector();
				AbstractDAO<Brand> brandDao = new BrandDAOImpl(connector.getConnection())) {

			return brandDao.getAll();

		} catch (DAOException e) {
			throw new ServiceLayerException(e);
		}
	}

	@Override
	public Optional<Brand> getEntityById(int id) throws ServiceLayerException {

		try (WrapperConnector connector = new WrapperConnector();
				AbstractDAO<Brand> brandDao = new BrandDAOImpl(connector.getConnection())) {

			return brandDao.getEntityById(id);

		} catch (DAOException e) {
			throw new ServiceLayerException(e);
		}
	}

	@Override
	public Optional<Brand> getEntityByBrandModel(String brand, String model) throws ServiceLayerException {

		try (WrapperConnector connector = new WrapperConnector();
				BrandDAOImpl brandDao = new BrandDAOImpl(connector.getConnection())) {

			return brandDao.getEntityByBrandModel(brand, model);

		} catch (DAOException e) {
			throw new ServiceLayerException(e);
		}
	}

	@Override
	public boolean addBrand(Brand brand) throws ServiceLayerException {

		try (WrapperConnector connector = new WrapperConnector();
				BrandDAOImpl brandDao = new BrandDAOImpl(connector.getConnection())) {

			return brandDao.addEntity(brand);

		} catch (DAOException e) {
			throw new ServiceLayerException(e);
		}
	}

	@Override
	public boolean delete(int id) throws ServiceLayerException {

		try (WrapperConnector connector = new WrapperConnector();
				AbstractDAO<Brand> brandDao = new BrandDAOImpl(connector.getConnection())) {

			return brandDao.delete(id);

		} catch (DAOException e) {
			throw new ServiceLayerException(e);
		}
	}
}
