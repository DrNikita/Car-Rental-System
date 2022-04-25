package by.epam.lab.mvc.connectionpool;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConnectionFactory {

	private static final Logger logger = LogManager.getLogger();
	private static final Properties PROPERTIES = new Properties();
	private static final String DATABASE_PROPERTIES = "database.properties";
	private static final String PROPERTY_URL = "db.url";
	private static final String PROPERTY_PASSWORD = "db.password";
	private static final String PROPERTY_USER = "db.user";
	private static final String PROPERTY_DRIVER = "db.driver";
	private static final String DATABASE_URL;
	private static final String DATABASE_PASSWORD;
	private static final String DATABASE_USER;
	private static final String DATABASE_DRIVER;

	static {
		try (InputStream inputStream = ConnectionFactory.class.getClassLoader()
				.getResourceAsStream(DATABASE_PROPERTIES)) {

			PROPERTIES.load(inputStream);
			DATABASE_URL = PROPERTIES.getProperty(PROPERTY_URL);
			DATABASE_PASSWORD = PROPERTIES.getProperty(PROPERTY_PASSWORD);
			DATABASE_USER = PROPERTIES.getProperty(PROPERTY_USER);
			DATABASE_DRIVER = PROPERTIES.getProperty(PROPERTY_DRIVER);
			Class.forName(DATABASE_DRIVER);

		} catch (FileNotFoundException e) {
			logger.log(Level.FATAL, "FileNotFoundException " + e.getMessage());
			throw new RuntimeException(e);

		} catch (IOException e) {
			logger.log(Level.FATAL, "IOException " + e.getMessage());
			throw new RuntimeException(e);

		} catch (ClassNotFoundException e) {
			logger.log(Level.FATAL, "driver: " + PROPERTIES.getProperty(PROPERTY_DRIVER) + "not found");
			throw new RuntimeException(e);
		}
	}

	private ConnectionFactory() {
	}

	static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
	}
}
