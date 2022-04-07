package by.epam.lab.property_manager;

import java.util.ResourceBundle;

public class ConfigurationManager {
	private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("properties.config");

	private ConfigurationManager() {
	}

	public static String getProperty(String key) {
		return resourceBundle.getString(key);
	}
}
