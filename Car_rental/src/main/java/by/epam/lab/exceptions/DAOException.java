package by.epam.lab.exceptions;

public class DAOException extends Throwable {

	public DAOException() {
		super();
	}

	public DAOException(String message, Throwable cause) {
		super(message, cause);
	}

	public DAOException(String message) {
		super(message);
	}

	public DAOException(Throwable cause) {
		super(cause);

	}
}
