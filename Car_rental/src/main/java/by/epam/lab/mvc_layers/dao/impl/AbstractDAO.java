package by.epam.lab.mvc_layers.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.lab.entity.Entity;
import by.epam.lab.exceptions.DAOException;

public abstract class AbstractDAO<T extends Entity> {

	private static final Logger logger = LogManager.getLogger();

	protected Connection connection;

	public AbstractDAO(Connection connection) {
		this.connection = connection;
	}

	public abstract List<T> getAll() throws DAOException;

	public abstract Optional<T> getEntityById(int id) throws DAOException;

	public abstract boolean delete(int id) throws DAOException;

	public abstract T create(ResultSet resultSet) throws DAOException, SQLException;

	public void close(Statement st) {
		try {
			if (st != null) {
				st.close();
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, "Statement closin error.");
		}
	}
}
