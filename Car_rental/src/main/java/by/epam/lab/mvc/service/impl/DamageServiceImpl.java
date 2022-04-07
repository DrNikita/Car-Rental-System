package by.epam.lab.mvc.service.impl;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.lab.entity.Damage;
import by.epam.lab.exceptions.DAOException;
import by.epam.lab.exceptions.ServiceLayerException;
import by.epam.lab.mvc.connectionpool.WrapperConnector;
import by.epam.lab.mvc.dao.AbstractDAO;
import by.epam.lab.mvc.dao.impl.DamageDAOImpl;
import by.epam.lab.mvc.service.IDamageService;

public class DamageServiceImpl implements IDamageService {

	private static final Logger logger = LogManager.getLogger();

	@Override
	public List<Damage> getAll() throws ServiceLayerException {

		try (WrapperConnector connector = new WrapperConnector();
				AbstractDAO<Damage> damageDao = new DamageDAOImpl(connector.getConnection());) {

			return damageDao.getAll();

		} catch (

		DAOException e) {
			throw new ServiceLayerException(e);
		}
	}

	@Override
	public Optional<Damage> getEntityById(int id) throws ServiceLayerException {

		try (WrapperConnector connector = new WrapperConnector();
				AbstractDAO<Damage> damageDao = new DamageDAOImpl(connector.getConnection());) {

			return damageDao.getEntityById(id);

		} catch (DAOException e) {
			throw new ServiceLayerException(e);
		}
	}

	@Override
	public boolean addDamage(Damage damage) throws ServiceLayerException {

		try (WrapperConnector connector = new WrapperConnector();
				DamageDAOImpl damageDao = new DamageDAOImpl(connector.getConnection());) {

			return damageDao.addDamage(damage);

		} catch (DAOException e) {
			logger.log(Level.ERROR, "DAOException in " + this.getClass().getName() + ": " + e);
			throw new ServiceLayerException(e);
		}
	}

	@Override
	public boolean payDamage(int id) throws ServiceLayerException {

		try (WrapperConnector connector = new WrapperConnector();
				DamageDAOImpl damageDao = new DamageDAOImpl(connector.getConnection())) {

			return damageDao.payDamage(id);

		} catch (DAOException e) {
			throw new ServiceLayerException(e);
		}
	}

	@Override
	public boolean delete(int id) throws ServiceLayerException {

		try (WrapperConnector connector = new WrapperConnector();
				AbstractDAO<Damage> damageDao = new DamageDAOImpl(connector.getConnection());) {

			return damageDao.delete(id);

		} catch (DAOException e) {
			throw new ServiceLayerException(e);
		}
	}
}
