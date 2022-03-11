package by.epam.lab.mvc_layers.dao;

import java.sql.Connection;

import by.epam.lab.entity.Damage;

public abstract class AbstractDamageDAO extends AbstractDAO<Damage> {

	public AbstractDamageDAO(Connection connection) {
		super(connection);
	}

}
