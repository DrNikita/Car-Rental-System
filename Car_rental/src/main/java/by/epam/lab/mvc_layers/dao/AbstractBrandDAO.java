package by.epam.lab.mvc_layers.dao;

import java.sql.Connection;

import by.epam.lab.entity.Brand;

public abstract class AbstractBrandDAO extends AbstractDAO<Brand> {

	public AbstractBrandDAO(Connection connection) {
		super(connection);
	}
}
