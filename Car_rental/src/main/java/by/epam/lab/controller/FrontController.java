package by.epam.lab.controller;

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

import by.epam.lab.command.ActionCommand;
import by.epam.lab.command.ActionFactory;
import by.epam.lab.utils.ConfigurationManager;
import by.epam.lab.utils.MessageManager;

@WebServlet("/controller")
public class FrontController extends HttpServlet {

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

		ActionFactory client = new ActionFactory();
		ActionCommand command = client.defineCommand(request);
		Router router = command.execute(request);

		switch (router.getType()) {

		case FORWARD:
			logger.log(Level.DEBUG, "forward from frontC");
			RequestDispatcher dispatcher = request.getRequestDispatcher(router.getPagePath());
			dispatcher.forward(request, response);
			break;

		case REDIRECT:
			logger.log(Level.DEBUG, "redirect from frontC");
			response.sendRedirect(router.getPagePath());
			break;

		default:
			logger.log(Level.ERROR, "Incorrect router type: " + router.getType());
			request.getSession().setAttribute("nullPage", MessageManager.getProperty("message.nullpage"));
			response.sendRedirect(request.getContextPath() + ConfigurationManager.getProperty("path.page.index"));
		}
	}
}
