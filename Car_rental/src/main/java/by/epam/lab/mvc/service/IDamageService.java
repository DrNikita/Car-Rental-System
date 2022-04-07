package by.epam.lab.mvc.service;

import by.epam.lab.entity.Damage;
import by.epam.lab.exceptions.ServiceLayerException;

public interface IDamageService extends IService<Damage> {

	boolean addDamage(Damage damage) throws ServiceLayerException;

	boolean payDamage(int id) throws ServiceLayerException;
}
