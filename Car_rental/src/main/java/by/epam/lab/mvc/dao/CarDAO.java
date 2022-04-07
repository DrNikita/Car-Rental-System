package by.epam.lab.mvc.dao;

import by.epam.lab.entity.Brand;
import by.epam.lab.entity.Car;
import by.epam.lab.exceptions.DAOException;

public interface CarDAO {

	public boolean addCar(Car car) throws DAOException;

	public boolean changeBrand(Brand brand, int id) throws DAOException;

	public boolean changeYearOfIssue(int year, int id) throws DAOException;

	public boolean changeCarPrice(int price, int id) throws DAOException;

	public boolean changeCarImage(String link, int id) throws DAOException;
}
