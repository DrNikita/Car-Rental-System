package by.epam.lab.exceptions;

public class ApplicationInitializationException extends Exception {

	public ApplicationInitializationException() {
		super();
	}

	public ApplicationInitializationException(String message, Throwable cause) {
		super(message, cause);
	}

	public ApplicationInitializationException(String message) {
		super(message);
	}

	public ApplicationInitializationException(Throwable cause) {
		super(cause);
	}
}
