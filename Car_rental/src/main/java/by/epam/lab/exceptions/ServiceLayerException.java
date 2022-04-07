package by.epam.lab.exceptions;

public class ServiceLayerException extends Exception {

	private static final long serialVersionUID = -2156959074462532138L;

	public ServiceLayerException() {
		super();
	}

	public ServiceLayerException(String message, Throwable cause) {
		super(message, cause);
	}

	public ServiceLayerException(String message) {
		super(message);
	}

	public ServiceLayerException(Throwable cause) {
		super(cause);
	}
}
