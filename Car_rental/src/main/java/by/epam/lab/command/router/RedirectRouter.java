package by.epam.lab.command.router;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RedirectRouter implements Router {

	private String pagePath;

	public RedirectRouter() {
	}

	public RedirectRouter(String pagePath) {
		this.pagePath = pagePath;
	}

	@Override
	public void send(HttpServletRequest request, HttpServletResponse response) {

		try {
			response.sendRedirect(pagePath);
		} catch (IOException e) {
		}
	}
}
