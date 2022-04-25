package by.epam.lab.mvc.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import by.epam.lab.entity.Order;
import by.epam.lab.exceptions.DAOException;
import by.epam.lab.exceptions.ServiceLayerException;
import by.epam.lab.mvc.connectionpool.WrapperConnector;
import by.epam.lab.mvc.dao.AbstractDAO;
import by.epam.lab.mvc.dao.impl.OrderDAOImpl;
import by.epam.lab.mvc.service.IOrderService;

public class OrderServiceImpl implements IOrderService {

	@Override
	public List<Order> getAll() throws ServiceLayerException {

		try (WrapperConnector connector = new WrapperConnector();
				AbstractDAO<Order> orderDao = new OrderDAOImpl(connector.getConnection())) {

			return orderDao.getAll();

		} catch (DAOException e) {
			throw new ServiceLayerException(e);
		}
	}

	@Override
	public List<Order> getOrdersOfUser(int id) throws ServiceLayerException {

		try (WrapperConnector connector = new WrapperConnector();
				OrderDAOImpl orderDao = new OrderDAOImpl(connector.getConnection())) {

			return orderDao.getOrdersOfUser(id);

		} catch (DAOException e) {
			throw new ServiceLayerException(e);
		}
	}

	@Override
	public List<Order> getOrdersForCar(int id) throws ServiceLayerException {

		try (WrapperConnector connector = new WrapperConnector();
				OrderDAOImpl orderDao = new OrderDAOImpl(connector.getConnection())) {

			return orderDao.getOrdersForCar(id);

		} catch (DAOException e) {
			throw new ServiceLayerException(e);
		}
	}

	@Override
	public List<Order> getPaidOrders() throws ServiceLayerException {

		try (WrapperConnector connector = new WrapperConnector();
				OrderDAOImpl orderDao = new OrderDAOImpl(connector.getConnection())) {

			return orderDao.getPaidOrders();

		} catch (DAOException e) {
			throw new ServiceLayerException(e);
		}
	}

	@Override
	public List<Order> getUnpaidOrders() throws ServiceLayerException {

		try (WrapperConnector connector = new WrapperConnector();
				OrderDAOImpl orderDao = new OrderDAOImpl(connector.getConnection())) {

			return orderDao.getUnpaidOrders();

		} catch (DAOException e) {
			throw new ServiceLayerException(e);
		}
	}

	@Override
	public List<Order> getNotConsidered() throws ServiceLayerException {

		try (WrapperConnector connector = new WrapperConnector();
				OrderDAOImpl orderDao = new OrderDAOImpl(connector.getConnection())) {

			return orderDao.getNotConsidered();

		} catch (DAOException e) {
			throw new ServiceLayerException(e);
		}
	}

	@Override
	public Optional<Order> getEntityById(int id) throws ServiceLayerException {

		try (WrapperConnector connector = new WrapperConnector();
				OrderDAOImpl orderDao = new OrderDAOImpl(connector.getConnection())) {

			return orderDao.getEntityById(id);

		} catch (DAOException e) {
			throw new ServiceLayerException(e);
		}
	}

	@Override
	public boolean confirmOrder(int id) throws ServiceLayerException {

		try (WrapperConnector connector = new WrapperConnector();
				OrderDAOImpl orderDao = new OrderDAOImpl(connector.getConnection())) {

			return orderDao.confirmOrder(id);

		} catch (DAOException e) {
			throw new ServiceLayerException(e);
		}
	}

	@Override
	public boolean rejectOrder(int id, String reason) throws ServiceLayerException {

		try (WrapperConnector connector = new WrapperConnector();
				OrderDAOImpl orderDao = new OrderDAOImpl(connector.getConnection())) {

			return orderDao.rejectOrder(id, reason);

		} catch (DAOException e) {
			throw new ServiceLayerException(e);
		}
	}

	@Override
	public boolean payOrder(int id) throws ServiceLayerException {

		try (WrapperConnector connector = new WrapperConnector();
				OrderDAOImpl orderDao = new OrderDAOImpl(connector.getConnection())) {

			return orderDao.payOrder(id);

		} catch (DAOException e) {
			throw new ServiceLayerException(e);
		}
	}

	@Override
	public boolean changeStartDate(Date date, int id) throws ServiceLayerException {

		try (WrapperConnector connector = new WrapperConnector();
				OrderDAOImpl orderDao = new OrderDAOImpl(connector.getConnection())) {

			return orderDao.changeStartDate(date, id);

		} catch (DAOException e) {
			throw new ServiceLayerException(e);
		}
	}

	@Override
	public boolean changeEndDate(Date date, int id) throws ServiceLayerException {

		try (WrapperConnector connector = new WrapperConnector();
				OrderDAOImpl orderDao = new OrderDAOImpl(connector.getConnection())) {

			return orderDao.changeEndDate(date, id);

		} catch (DAOException e) {
			throw new ServiceLayerException(e);
		}
	}

	@Override
	public boolean changeConfirmationStatus(int status, int orderId) throws ServiceLayerException {

		try (WrapperConnector connector = new WrapperConnector();
				OrderDAOImpl orderDao = new OrderDAOImpl(connector.getConnection())) {

			return orderDao.changeConfirmationStatus(status, orderId);

		} catch (DAOException e) {
			throw new ServiceLayerException(e);
		}
	}

	@Override
	public boolean changeIsPaidStatus(boolean status, int orderId) throws ServiceLayerException {

		try (WrapperConnector connector = new WrapperConnector();
				OrderDAOImpl orderDao = new OrderDAOImpl(connector.getConnection())) {

			return orderDao.changeIsPaidStatus(status, orderId);

		} catch (DAOException e) {
			throw new ServiceLayerException(e);
		}
	}

	@Override
	public boolean changeCar(int carId, int orderId) throws ServiceLayerException {

		try (WrapperConnector connector = new WrapperConnector();
				OrderDAOImpl orderDao = new OrderDAOImpl(connector.getConnection())) {

			return orderDao.changeCar(carId, orderId);

		} catch (DAOException e) {
			throw new ServiceLayerException(e);
		}
	}

	@Override
	public boolean changePrice(int price, int orderId) throws ServiceLayerException {

		try (WrapperConnector connector = new WrapperConnector();
				OrderDAOImpl orderDao = new OrderDAOImpl(connector.getConnection())) {

			return orderDao.changePrice(price, orderId);

		} catch (DAOException e) {
			throw new ServiceLayerException(e);
		}
	}

	@Override
	public boolean addOrder(Order order) throws ServiceLayerException {

		try (WrapperConnector connector = new WrapperConnector();
				OrderDAOImpl orderDao = new OrderDAOImpl(connector.getConnection())) {

			return orderDao.addEntity(order);

		} catch (DAOException e) {
			throw new ServiceLayerException(e);
		}
	}

	@Override
	public boolean delete(int id) throws ServiceLayerException {

		try (WrapperConnector connector = new WrapperConnector();
				OrderDAOImpl orderDao = new OrderDAOImpl(connector.getConnection())) {

			return orderDao.delete(id);

		} catch (DAOException e) {
			throw new ServiceLayerException(e);
		}
	}
}
