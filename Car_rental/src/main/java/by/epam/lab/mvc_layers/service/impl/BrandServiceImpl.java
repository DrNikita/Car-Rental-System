package by.epam.lab.mvc_layers.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import by.epam.lab.entity.Brand;
import by.epam.lab.exceptions.DAOException;
import by.epam.lab.exceptions.ServiceLayerException;
import by.epam.lab.mvc_layers.connectionpool.ConnectionPool;
import by.epam.lab.mvc_layers.dao.AbstractDAO;
import by.epam.lab.mvc_layers.dao.impl.BrandDAOImpl;
import by.epam.lab.mvc_layers.service.IBrandService;

public class BrandServiceImpl implements IBrandService {

	@Override
	public List<Brand> getAll() throws ServiceLayerException {
		try {

			ConnectionPool connectionPool = ConnectionPool.getInstance();
			Connection conn = connectionPool.getConnection();

			List<Brand> brands = new ArrayList<>();

			conn.setAutoCommit(false);

			AbstractDAO<Brand> brandDao = new BrandDAOImpl(conn);
			brands = brandDao.getAll();

			conn.commit();

			return brands;

		} catch (SQLException | DAOException e) {
			throw new ServiceLayerException(e);
		}
	}

	@Override
	public Optional<Brand> getEntityById(int id) throws ServiceLayerException {
		try {

			ConnectionPool connectionPool = ConnectionPool.getInstance();
			Connection conn = connectionPool.getConnection();

			conn.setAutoCommit(false);

			AbstractDAO<Brand> brandDao = new BrandDAOImpl(conn);
			Optional<Brand> brand = brandDao.getEntityById(id);

			conn.commit();

			return brand;

		} catch (SQLException | DAOException e) {
			throw new ServiceLayerException(e);
		}
	}

	@Override
	public boolean delete(int id) throws ServiceLayerException {
		try {

			ConnectionPool connectionPool = ConnectionPool.getInstance();
			Connection conn = connectionPool.getConnection();

			conn.setAutoCommit(false);

			AbstractDAO<Brand> brandDao = new BrandDAOImpl(conn);
			((BrandDAOImpl) brandDao).delete(id);

			conn.commit();

			return true;

		} catch (SQLException | DAOException e) {
			throw new ServiceLayerException(e);
		}
	}

}
