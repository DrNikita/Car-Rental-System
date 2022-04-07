package by.epam.lab.mvc.dao;

import java.util.Optional;

import by.epam.lab.entity.Brand;
import by.epam.lab.exceptions.DAOException;

public interface BrandDAO {

	public boolean addBrand(Brand brand) throws DAOException;

	public Optional<Brand> getEntityByBrandModel(String brand, String model) throws DAOException;
}
