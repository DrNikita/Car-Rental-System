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

import by.epam.lab.entity.Car;
import by.epam.lab.exceptions.DAOException;
import by.epam.lab.mvc_layers.connectionpool.ConnectionPool;
import by.epam.lab.mvc_layers.dao.CarDAO;
import by.epam.lab.utils.Constants;

public class CarDAOImpl implements CarDAO {

	private static final Logger logger = LogManager.getLogger();

	private static final String SQL_ADD_CAR = "INSERT INTO cars (brand,model,price,image_link,description,rental_dates) values(?,?,?,?,?,?)";
	private static final String SQL_GET_CARS_BY_BRAND = "SELECT car_id,brand,model,price,image_link,description,rental_dates FROM cars WHERE brand=?";
	private static final String SQL_GET_CARS_BY_MODEL = "SELECT car_id,brand,model,price,image_link,description,rental_dates FROM cars WHERE model=?";
	private static final String SQL_GET_ALL_CARS = "SELECT * FROM cars";
	private static final String SQL_GET_CAR_BY_ID = "SELECT car_id,brand,model,price,image_link,description,rental_dates FROM cars WHERE car_id=?";
	private static final String SQL_SET_PRICE = "UPDATE cars SET price=? where car_id=?";
	private static final String SQL_SET_DESCRIPTION = "UPDATE cars SET description=? where car_id=?";
	private static final String SQL_SET_IMAGE_LINK = "UPDATE cars SET image_link=? where car_id=?";
	private static final String SQL_DELETE_CAR = "DELETE FROM cars WHERE car_id=?";
	ConnectionPool connectionPool = ConnectionPool.getInstance();

	@Override
	public void addCar(Car car) throws DAOException {
		logger.log(Level.INFO, "Adding car to the db" + car);

		try (Connection connection = connectionPool.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_ADD_CAR)) {

			statement.setString(1, car.getBrand());
			statement.setString(2, car.getModel());
			statement.setInt(3, car.getPrice());
			statement.setString(4, car.getImageLink());
			statement.setString(5, car.getDescription());
			statement.setString(6, car.getRentalDates().toString());
			int rowCount = statement.executeUpdate();

		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQL EXCEPTION " + e.getMessage() + "-" + e.getErrorCode());
			throw new DAOException("Dao exception in method addCar. user: " + car, e);
		}
	}

	@Override
	public Optional<Car> getCarById(int id) throws DAOException {
		logger.log(Level.INFO, "Find car by id = " + id);

		Optional<Car> car = null;

		try (Connection connection = connectionPool.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_GET_CAR_BY_ID)) {

			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				car = Optional.of(createCar(resultSet));
			}

		} catch (SQLException e) {
			throw new DAOException("Dao exception", e);
		}

		return car;
	}

	@Override
	public List<Car> getCarsByBrand(String brand) throws DAOException {
		logger.log(Level.INFO, "Find user by name, name=  " + brand);

		List<Car> cars = new ArrayList<>();

		try (Connection connection = connectionPool.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_GET_CARS_BY_BRAND)) {

			statement.setString(1, brand);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				cars.add(createCar(resultSet));
			}

		} catch (SQLException e) {
			throw new DAOException("Dao exception", e);
		}

		return cars;
	}

	@Override
	public List<Car> getCarsByModel(String model) throws DAOException {
		logger.log(Level.INFO, "Find user by name, name=  " + model);

		List<Car> cars = new ArrayList<>();

		try (Connection connection = connectionPool.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_GET_CARS_BY_MODEL)) {

			statement.setString(1, model);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				cars.add(createCar(resultSet));
			}

		} catch (SQLException e) {
			throw new DAOException("Dao exception", e);
		}

		return cars;
	}

	@Override
	public Optional<Car> getCarByBrandModel(String brand, String model) throws DAOException {
		return null;
	}

	@Override
	public List<Car> getAllCars() throws DAOException {
		logger.log(Level.INFO, "Find all cars");

		List<Car> cars = new ArrayList<Car>();

		try (Connection connection = connectionPool.getConnection();

				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(SQL_GET_ALL_CARS)) {

			while (resultSet.next()) {
				cars.add(createCar(resultSet));
			}

		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQLException in findAllCars: " + e.getMessage() + " : " + e.getErrorCode());
			throw new DAOException("Dao exception", e);
		}

		return cars;
	}

	@Override
	public void changeCarPrice(int price, int id) throws DAOException {
		try (Connection connection = connectionPool.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_SET_PRICE)) {

			statement.setInt(1, price);
			statement.setInt(2, id);
			statement.executeUpdate();

		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public void changeCarImage(String link, int id) throws DAOException {
		try (Connection connection = connectionPool.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_SET_IMAGE_LINK)) {

			statement.setString(1, link);
			statement.setInt(2, id);
			statement.executeUpdate();

		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public void changeDescriprion(String description, int id) throws DAOException {
		try (Connection connection = connectionPool.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_SET_DESCRIPTION)) {

			statement.setString(1, description);
			statement.setInt(2, id);
			statement.executeUpdate();

		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public void deleteCar(int id) throws DAOException {
		try (Connection connection = connectionPool.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_DELETE_CAR)) {

			statement.setInt(1, id);
			statement.executeUpdate();

		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	private Car createCar(ResultSet resultSet) throws SQLException {

		Car car;

		int carId = resultSet.getInt(Constants.CAR_ID);
		String brand = resultSet.getString(Constants.CAR_BRAND);
		String model = resultSet.getString(Constants.CAR_MODEL);
		int price = resultSet.getInt(Constants.CAR_PRICE);
		String imageLink = resultSet.getString(Constants.CAR_IMAGE_LINK);
		String description = resultSet.getString(Constants.CAR_DESCRIPTION);

		car = new Car.Builder().setCarId(carId).setBrand(brand).setModel(model).setPrice(price).setImageLink(imageLink)
				.setDescription(description).build();

		return car;
	}
}
