package by.epam.lab.main_page_servlet;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.lab.entity.Car;
import by.epam.lab.exceptions.DAOException;
import by.epam.lab.mvc_layers.dao.impl.CarDAOImpl;
import by.epam.lab.utils.ConfigurationManager;

@WebServlet("/carDescription")
public class CarDescriptionServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, IllegalArgumentException {
		processRequest(request, response);
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, IllegalArgumentException {

		CarDAOImpl carDaoImpl = new CarDAOImpl();
		Optional<Car> car;

		try {
			int carId = Integer.parseInt(request.getParameter("carId"));
			car = carDaoImpl.getCarById(carId);

			if (car.isPresent()) {
				request.setAttribute("car", car.get());
			}
			getServletContext().getRequestDispatcher(ConfigurationManager.getProperty("path.page.carDescription"))
					.forward(request, response);

		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException(e);
		} catch (DAOException e) {
			getServletContext().getRequestDispatcher(ConfigurationManager.getProperty("path.page.main"))
					.forward(request, response);
		}
	}
}
