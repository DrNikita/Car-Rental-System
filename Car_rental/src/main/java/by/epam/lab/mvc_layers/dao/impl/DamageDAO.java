package by.epam.lab.mvc_layers.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import by.epam.lab.entity.Damage;
import by.epam.lab.exceptions.DAOException;

public class DamageDAO extends AbstractDAO<Damage> {

	public DamageDAO(Connection connection) {
		super(connection);
	}

	@Override
	public List<Damage> getAll() throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Damage> getEntityById(int id) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(int id) throws DAOException {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Damage create(ResultSet resultSet) throws DAOException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
