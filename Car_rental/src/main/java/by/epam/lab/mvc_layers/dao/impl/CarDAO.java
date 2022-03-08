package by.epam.lab.mvc_layers.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.lab.entity.Brand;
import by.epam.lab.entity.Car;
import by.epam.lab.exceptions.DAOException;
import by.epam.lab.utils.DAOConstants;

public class CarDAO extends AbstractDAO<Car> {

	private static final Logger logger = LogManager.getLogger();

	private static final String SQL_GET_ALL_CARS = "SELECT * FROM car_park";
	private static final String SQL_GET_CAR_BY_ID = "SELECT id_car,brand_id,year_of_issue,price,image_link FROM car_park WHERE id_car=?";
	private static final String SQL_GET_BRAND = "SELECT brand FROM brands WHERE id_brand=?";
	private static final String SQL_GET_MODEL = "SELECT model FROM brands WHERE id_brand=?";
	private static final String SQL_SET_PRICE = "UPDATE car_park SET price=? where id_car=?";
	private static final String SQL_SET_IMAGE_LINK = "UPDATE car_park SET image_link=? where id_car=?";
	private static final String SQL_DELETE_CAR = "DELETE FROM car_park WHERE id_car=?";
	private static final String SQL_ADD_CAR = "INSERT INTO car_park (brand_id,year_of_issue,price,image_link) values(?,?,?,?)";

	public CarDAO(Connection connection) {
		super(connection);
	}

	public boolean addCar(Car car) throws DAOException {
		logger.log(Level.INFO, "Adding car to the db" + car);

		try (PreparedStatement statement = connection.prepareStatement(SQL_ADD_CAR)) {

			statement.setInt(1, car.getBrand().getId());
			statement.setInt(2, car.getYearOfIssue());
			statement.setInt(3, car.getPrice());
			statement.setString(4, car.getImageLink());
			statement.executeUpdate();
			return true;

		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQL EXCEPTION " + e.getMessage() + "-" + e.getErrorCode());
			throw new DAOException("Dao exception in method addCar. user: " + car, e);
		}
	}

	public String getBrand(int idBrand) throws DAOException {

		String brand = "";

		try (PreparedStatement statement = connection.prepareStatement(SQL_GET_BRAND)) {

			statement.setInt(1, idBrand);
			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				brand = resultSet.getString(DAOConstants.BRAND);
			}

			return brand;

		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	public String getModel(int idBrand) throws DAOException {

		String model = "";

		try (PreparedStatement statement = connection.prepareStatement(SQL_GET_MODEL)) {

			statement.setInt(1, idBrand);
			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				model = resultSet.getString(DAOConstants.MODEL);
			}

			return model;

		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	public Optional<Car> getEntityById(int id) throws DAOException {
		logger.log(Level.INFO, "Find car by id = " + id);

		Optional<Car> car = null;

		try (PreparedStatement statement = connection.prepareStatement(SQL_GET_CAR_BY_ID)) {

			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				car = Optional.of(create(resultSet));
			}

		} catch (SQLException e) {
			throw new DAOException("Dao exception", e);
		}

		return car;
	}

	@Override
	public List<Car> getAll() throws DAOException {
		logger.log(Level.INFO, "Find all cars");

		List<Car> cars = new ArrayList<Car>();

		try (

				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(SQL_GET_ALL_CARS)) {

			while (resultSet.next()) {
				cars.add(create(resultSet));
			}

			return cars;

		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQLException in findAllCars: " + e.getMessage() + " : " + e.getErrorCode());
			throw new DAOException("Dao exception", e);
		}
	}

	public boolean changeCarPrice(int price, int id) throws DAOException {
		try (PreparedStatement statement = connection.prepareStatement(SQL_SET_PRICE)) {

			statement.setInt(1, price);
			statement.setInt(2, id);
			statement.executeUpdate();
			return true;

		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	public boolean changeCarImage(String link, int id) throws DAOException {
		try (PreparedStatement statement = connection.prepareStatement(SQL_SET_IMAGE_LINK)) {

			statement.setString(1, link);
			statement.setInt(2, id);
			statement.executeUpdate();
			return true;

		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public boolean delete(int id) throws DAOException {
		try (PreparedStatement statement = connection.prepareStatement(SQL_DELETE_CAR)) {

			statement.setInt(1, id);
			statement.executeUpdate();
			return true;

		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public Car create(ResultSet resultSet) throws DAOException, SQLException {

		AbstractDAO<Brand> brandDao = new BrandDAO(connection);

		int carId = resultSet.getInt(DAOConstants.ID_CAR);
		int brandId = resultSet.getInt(DAOConstants.CAR_BRAND_ID);
		Brand brand = brandDao.getEntityById(brandId).get();
		int yearOfIssue = resultSet.getInt(DAOConstants.CAR_YEAR_OF_ISSUE);
		int price = resultSet.getInt(DAOConstants.CAR_PRICE);
		String imageLink = resultSet.getString(DAOConstants.CAR_IMAGE_LINK);

		Car car = new Car.Builder().setCarId(carId).setBrand(brand).setYearOfIssue(yearOfIssue).setPrice(price)
				.setImageLink(imageLink).build();

		return car;
	}
}
