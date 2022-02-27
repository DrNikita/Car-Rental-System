package by.epam.lab.validations;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserDataValidation {

	public static final Logger logger = LogManager.getLogger();
	private final static String EMAIL_REGEX = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
	private final static String NAME_REGEX = "^[a-zA-Z0-9]+([._]?[a-zA-Z0-9]+)*$";
	private final static String PASSWORD_REGEX = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$";
	private static final String PHONE_REGEX = "^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\\s\\./0-9]*$";

	public static boolean isNameValid(String name) {
		return name.matches(NAME_REGEX);
	}

	public static boolean isEmailValid(String email) {
		return email.matches(EMAIL_REGEX);
	}

	public static boolean isPasswordValid(String password) {
		return password.matches(PASSWORD_REGEX);
	}

	public static boolean isPhoneValid(String password) {
		return password.matches(PHONE_REGEX);
	}
}
