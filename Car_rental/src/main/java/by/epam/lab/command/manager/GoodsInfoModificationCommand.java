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

public class GoodsInfoModificationCommand implements ActionCommand {

	private static final Logger logger = LogManager.getLogger();

	@Override
	public Router execute(HttpServletRequest request) {

		try {

			ICarService carService = new CarServiceImpl();

			String parameter = request.getParameter("parameter");
			Car car = (Car) request.getSession().getAttribute(EntityesManager.getProperty("goods"));

			if (car == null) {
				logger.log(Level.INFO, this.getClass().getName() + ": Car wasn't found in session.");
				return new ForwardRouter(ConfigurationManager.getProperty("command.get_goods"));
			}

			switch (parameter) {

			case ("brand_model"):
				String brand = request.getParameter(EntityesManager.getProperty("brand"));
				String model = request.getParameter(EntityesManager.getProperty("model"));
				IBrandService brandService = new BrandServiceImpl();

				Optional<Brand> optionalBrand = brandService.getEntityByBrandModel(brand, model);

				if (optionalBrand.isPresent()) {
					carService.changeBrand(optionalBrand.get(), car.getId());

				} else {
					Brand brandObj = new Brand.Builder().setBrand(brand).setModel(model).build();
					brandService.addBrand(brandObj);
					carService.changeBrand(
							brandService.getEntityByBrandModel(brandObj.getBrand(), brandObj.getModel()).get(),
							car.getId());
				}
				break;

			case ("year"):
				int year = Integer.parseInt(request.getParameter(EntityesManager.getProperty("year")));
				carService.changeYearOfIssue(year, car.getId());
				break;

			case ("price"):
				int price = Integer.parseInt(request.getParameter(EntityesManager.getProperty("price")));
				carService.changeCarPrice(price, car.getId());
				break;

			case ("image"):
				String image = request.getParameter(EntityesManager.getProperty("image"));
				carService.changeCarImage(image, car.getId());
				break;
			}

			return new ForwardRouter(ConfigurationManager.getProperty("command.get_goods"));

		} catch (ServiceLayerException e) {

			logger.log(Level.ERROR, "ServiceException in method execute " + e);
			return new ErrorRouter(e);
		}
	}
}
