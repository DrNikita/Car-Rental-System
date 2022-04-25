package by.epam.lab.command.manager;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.lab.command.ActionCommand;
import by.epam.lab.command.router.ErrorRouter;
import by.epam.lab.command.router.ForwardRouter;
import by.epam.lab.command.router.Router;
import by.epam.lab.entity.Brand;
import by.epam.lab.entity.Car;
import by.epam.lab.exceptions.ServiceLayerException;
import by.epam.lab.mvc.service.IBrandService;
import by.epam.lab.mvc.service.ICarService;
import by.epam.lab.mvc.service.impl.BrandServiceImpl;
import by.epam.lab.mvc.service.impl.CarServiceImpl;
import by.epam.lab.property_manager.ConfigurationManager;
import by.epam.lab.property_manager.EntityesManager;

public class AddCarCommand implements ActionCommand {

	private static final Logger logger = LogManager.getLogger();

	@Override
	public Router execute(HttpServletRequest request) {

		try {

			String brand = request.getParameter(EntityesManager.getProperty("brand"));
			String model = request.getParameter(EntityesManager.getProperty("model"));
			int year = Integer.parseInt(request.getParameter(EntityesManager.getProperty("year")));
			int price = Integer.parseInt(request.getParameter(EntityesManager.getProperty("price")));
			String imageLink = request.getParameter(EntityesManager.getProperty("image"));

			if (brand == null || model == null || imageLink == null) {
				return new ErrorRouter("Null data.");
			}

			Brand brandObj = new Brand.Builder().setBrand(brand).setModel(model).build();

			Car car;

			IBrandService brandService = new BrandServiceImpl();

			Optional<Brand> optionalBrand = brandService.getEntityByBrandModel(brandObj.getBrand(),
					brandObj.getModel());

			if (optionalBrand.isPresent()) {

				logger.log(Level.INFO, "Brand: " + brandObj + " already in DB");

				car = new Car.Builder().setBrand(optionalBrand.get()).setYearOfIssue(year).setPrice(price)
						.setImageLink(imageLink).build();

				logger.log(Level.INFO, "Car " + car.getBrand() + " was created");

			} else {
				brandService.addBrand(brandObj);

				optionalBrand = brandService.getEntityByBrandModel(brandObj.getBrand(), brandObj.getModel());

				logger.log(Level.INFO, "Brand: " + brandObj + " was added to DB");

				car = new Car.Builder().setBrand(optionalBrand.get()).setYearOfIssue(year).setPrice(price)
						.setImageLink(imageLink).build();

				logger.log(Level.INFO, "Car: " + car + " was created.");
			}

			ICarService carService = new CarServiceImpl();

			carService.addCar(car);

			logger.log(Level.INFO, "Car " + car + " was added");

			return new ForwardRouter(ConfigurationManager.getProperty("path.page.goods"));

		} catch (ServiceLayerException e) {
			logger.log(Level.ERROR, "Service exception in " + this.getClass().getName() + ": ", e);
			return new ErrorRouter(e);

		} catch (NumberFormatException e) {
			logger.log(Level.ERROR, "incorrect carId.", e);
			return new ErrorRouter(e);
		}
	}
}
