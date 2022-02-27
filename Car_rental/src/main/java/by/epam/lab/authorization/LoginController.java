package by.epam.lab.authorization;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.lab.commands.ActionCommand;
import by.epam.lab.mvc_layers.connectionpool.ConnectionPool;
import by.epam.lab.utils.ConfigurationManager;

@WebServlet("/controller")
public class LoginController extends HttpServlet {

	private static final Logger logger = LogManager.getLogger();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String page = null;

		ActionFactory client = new ActionFactory();
		ActionCommand command = client.defineCommand(request);

		page = command.execute(request);

		if (page != null) {
			logger.log(Level.INFO, "Forward to page: " + page);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
			dispatcher.forward(request, response);

		} else {
			logger.log(Level.INFO, "Null page. Redirect to index page.");
			page = ConfigurationManager.getProperty("path.page.index");
			request.getSession().setAttribute("nullPage", MessageManager.getProperty("message.nullpage"));
			response.sendRedirect(request.getContextPath() + page);
		}
	}
}
