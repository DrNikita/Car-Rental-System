package by.epam.lab.mvc.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import by.epam.lab.entity.Entity;
import by.epam.lab.exceptions.DAOException;

public abstract class AbstractDAO<T extends Entity> implements AutoCloseable {

	protected Connection connection;

	public AbstractDAO(Connection connector) {
		this.connection = connector;
	}

	public abstract List<T> getAll() throws DAOException;

	public abstract Optional<T> getEntityById(int id) throws DAOException;

	public abstract boolean addEntity(T entity) throws DAOException;

	public abstract boolean delete(int id) throws DAOException;

	public abstract T create(ResultSet resultSet) throws DAOException, SQLException;

	public void close() throws DAOException {

		try {
			connection.close();

		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}
}
