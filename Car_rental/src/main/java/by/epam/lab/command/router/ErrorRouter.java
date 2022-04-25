package by.epam.lab.command.router;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.lab.property_manager.ConfigurationManager;
import by.epam.lab.property_manager.EntityesManager;

public class ErrorRouter implements Router {

	private Exception e;
	private String message;

	public ErrorRouter() {
	}

	public ErrorRouter(String message) {
		this.message = message;
	}

	public ErrorRouter(Exception e) {
		this.e = e;
	}

	@Override
	public void send(HttpServletRequest request, HttpServletResponse response) {

		try {

			RequestDispatcher dispatcher = request
					.getRequestDispatcher(ConfigurationManager.getProperty("path.page.error"));

			if (e == null) {

				if (message == null) {
					dispatcher.forward(request, response);

				} else {
					request.setAttribute(EntityesManager.getProperty("servlet_name"),
							request.getAttribute(EntityesManager.getProperty("servlet_name")));
					request.setAttribute(EntityesManager.getProperty("exception"), message);
					dispatcher.forward(request, response);
				}

			} else {
				request.setAttribute(EntityesManager.getProperty("servlet_name"),
						EntityesManager.getProperty("servlet_name"));
				request.setAttribute(EntityesManager.getProperty("exception"), e);
				dispatcher.forward(request, response);
			}

		} catch (ServletException e) {
		} catch (IOException e) {
		}
	}
}
