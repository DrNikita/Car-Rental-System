package by.epam.lab.main_page_servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.lab.entity.Car;
import by.epam.lab.exceptions.DAOException;
import by.epam.lab.mvc_layers.dao.impl.CarDAOImpl;
import by.epam.lab.utils.ConfigurationManager;

@WebServlet("/main")
public class MainPageServlet extends HttpServlet {

	private static final Logger logger = LogManager.getLogger();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		CarDAOImpl carDao = new CarDAOImpl();

		try {

			List<Car> cars = carDao.getAllCars();

			request.getSession().setAttribute("cars", cars);

			logger.log(Level.INFO, "Forward to main page");
			getServletContext().getRequestDispatcher(ConfigurationManager.getProperty("path.page.main"))
					.forward(request, response);

		} catch (DAOException e) {
			logger.log(Level.INFO, "DAOException in " + this.getClass() + ": " + e);
			getServletContext().getRequestDispatcher(ConfigurationManager.getProperty("path.page.index"))
					.forward(request, response);

		}
	}
}
