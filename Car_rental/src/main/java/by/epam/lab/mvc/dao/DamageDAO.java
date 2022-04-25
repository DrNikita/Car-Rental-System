package by.epam.lab.mvc.dao;

import by.epam.lab.exceptions.DAOException;

public interface DamageDAO {

	public boolean payDamage(int id) throws DAOException;
}
