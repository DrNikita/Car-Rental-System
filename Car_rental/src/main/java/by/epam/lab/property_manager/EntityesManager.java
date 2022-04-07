package by.epam.lab.property_manager;

import java.util.ResourceBundle;

public class EntityesManager {
	private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("properties.entityes");

	private EntityesManager() {
	}

	public static String getProperty(String key) {
		return resourceBundle.getString(key);
	}
}
