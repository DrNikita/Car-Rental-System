package by.epam.lab.exceptions;

public class RouterException extends Exception {

	private static final long serialVersionUID = 1L;

	public RouterException() {
		super();
	}

	public RouterException(String message, Throwable cause) {
		super(message, cause);
	}

	public RouterException(String message) {
		super(message);
	}

	public RouterException(Throwable cause) {
		super(cause);
	}
}
