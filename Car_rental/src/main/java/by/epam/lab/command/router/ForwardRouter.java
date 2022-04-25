package by.epam.lab.command.router;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.lab.property_manager.ConfigurationManager;

public class ForwardRouter implements Router {

	private String pagePath;

	public ForwardRouter() {
		this.pagePath = ConfigurationManager.getProperty("path.command.logout");
	}

	public ForwardRouter(String pagePath) {
		this.pagePath = pagePath;
	}

	@Override
	public void send(HttpServletRequest request, HttpServletResponse response) {

		try {
			RequestDispatcher dispatcher = request.getRequestDispatcher(pagePath);
			dispatcher.forward(request, response);

		} catch (ServletException | IOException e) {
		}
	}
}
