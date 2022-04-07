package by.epam.lab.mvc.dao;

import by.epam.lab.entity.Damage;
import by.epam.lab.exceptions.DAOException;

public interface DamageDAO {

	public boolean addDamage(Damage damage) throws DAOException;

	public boolean payDamage(int id) throws DAOException;
}
