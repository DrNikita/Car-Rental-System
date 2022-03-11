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
import by.epam.lab.exceptions.DAOException;
import by.epam.lab.mvc_layers.dao.AbstractBrandDAO;
import by.epam.lab.utils.DAOConstants;

public class BrandDAOImpl extends AbstractBrandDAO {

	private static final Logger logger = LogManager.getLogger();

	private static final String SQL_GET_ALL_BRANDS = "SELECT * FROM brands";
	private static final String SQL_GET_BRAND_BY_ID = "SELECT id_brand,brand,model FROM brands WHERE id_brand=?";
	private static final String SQL_DELETE_BRAND = "DELETE FROM brands WHERE id_brand=?";

	public BrandDAOImpl(Connection connection) {
		super(connection);
	}

	@Override
	public List<Brand> getAll() throws DAOException {
		logger.log(Level.INFO, "Find all cars");

		try (

				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(SQL_GET_ALL_BRANDS)) {

			List<Brand> cars = new ArrayList<Brand>();

			while (resultSet.next()) {
				cars.add(create(resultSet));
			}

			return cars;

		} catch (SQLException e) {
			logger.log(Level.ERROR, "SQLException in findAllCars: " + e.getMessage() + " : " + e.getErrorCode());
			throw new DAOException("Dao exception", e);
		}
	}

	@Override
	public Optional<Brand> getEntityById(int id) throws DAOException {
		logger.log(Level.INFO, "Find car by id = " + id);

		try (PreparedStatement statement = connection.prepareStatement(SQL_GET_BRAND_BY_ID)) {

			Optional<Brand> brand = Optional.empty();

			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				brand = Optional.of(create(resultSet));
			}

			return brand;

		} catch (SQLException e) {
			throw new DAOException("Dao exception", e);
		}
	}

	@Override
	public boolean delete(int id) throws DAOException {
		try (PreparedStatement statement = connection.prepareStatement(SQL_DELETE_BRAND)) {

			statement.setInt(1, id);
			statement.executeUpdate();
			return true;

		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public Brand create(ResultSet resultSet) throws DAOException, SQLException {

		int brandId = resultSet.getInt(DAOConstants.BRAND_ID);
		String brandName = resultSet.getString(DAOConstants.BRAND_NAME);
		String modelName = resultSet.getString(DAOConstants.BRAND_MODEL);

		Brand brand = new Brand.Builder().setBrandId(brandId).setBrand(brandName).setModel(modelName).build();

		return brand;
	}

}
