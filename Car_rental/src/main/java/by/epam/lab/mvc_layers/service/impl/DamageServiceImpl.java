package by.epam.lab.mvc_layers.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import by.epam.lab.entity.Damage;
import by.epam.lab.exceptions.DAOException;
import by.epam.lab.exceptions.ServiceLayerException;
import by.epam.lab.mvc_layers.connectionpool.ConnectionPool;
import by.epam.lab.mvc_layers.dao.AbstractDAO;
import by.epam.lab.mvc_layers.dao.impl.DamageDAOImpl;
import by.epam.lab.mvc_layers.service.IDamageService;

public class DamageServiceImpl implements IDamageService {

	@Override
	public List<Damage> getAll() throws ServiceLayerException {

		try {

			ConnectionPool connectionPool = ConnectionPool.getInstance();
			Connection conn = connectionPool.getConnection();

			List<Damage> damage = new ArrayList<>();

			conn.setAutoCommit(false);

			AbstractDAO<Damage> damageDao = new DamageDAOImpl(conn);
			damage = damageDao.getAll();

			conn.commit();

			return damage;

		} catch (SQLException | DAOException e) {
			throw new ServiceLayerException(e);
		}
	}

	@Override
	public Optional<Damage> getEntityById(int id) throws ServiceLayerException {

		try {

			ConnectionPool connectionPool = ConnectionPool.getInstance();
			Connection conn = connectionPool.getConnection();

			conn.setAutoCommit(false);

			AbstractDAO<Damage> damageDao = new DamageDAOImpl(conn);
			Optional<Damage> damage = damageDao.getEntityById(id);

			conn.commit();

			return damage;

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

			AbstractDAO<Damage> damageDao = new DamageDAOImpl(conn);
			damageDao.delete(id);

			conn.commit();

			return true;

		} catch (SQLException | DAOException e) {
			throw new ServiceLayerException(e);
		}
	}

}
