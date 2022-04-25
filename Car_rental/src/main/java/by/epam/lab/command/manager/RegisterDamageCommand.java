package by.epam.lab.command.manager;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.lab.command.ActionCommand;
import by.epam.lab.command.router.ErrorRouter;
import by.epam.lab.command.router.ForwardRouter;
import by.epam.lab.command.router.Router;
import by.epam.lab.entity.Damage;
import by.epam.lab.entity.Order;
import by.epam.lab.exceptions.ServiceLayerException;
import by.epam.lab.mvc.service.IDamageService;
import by.epam.lab.mvc.service.impl.DamageServiceImpl;
import by.epam.lab.property_manager.ConfigurationManager;
import by.epam.lab.property_manager.EntityesManager;

public class RegisterDamageCommand implements ActionCommand {

	private static final Logger logger = LogManager.getLogger();

	@Override
	public Router execute(HttpServletRequest request) {

		try {
			Order order = (Order) request.getSession().getAttribute(EntityesManager.getProperty("order"));
			String description = request.getParameter(EntityesManager.getProperty("description"));
			int price = Integer.parseInt(request.getParameter(EntityesManager.getProperty("price")));

			if (description == null || description.trim().equals("")) {
				return new ErrorRouter("Empti damage description.");
			}

			Damage damage = new Damage.Builder().setUser(order.getUser()).setCar(order.getCar())
					.setDescriprion(description).setPrice(price).setIsPaid(false).build();

			IDamageService damageService = new DamageServiceImpl();
			damageService.addDamage(damage);

			return new ForwardRouter(ConfigurationManager.getProperty("path.page.to_manager_main"));

		} catch (ServiceLayerException e) {
			logger.log(Level.ERROR, e);
			return new ErrorRouter(e);

		} catch (NumberFormatException e) {
			logger.log(Level.ERROR, "incorrect carId.", e);
			return new ErrorRouter(e);
		}
	}
}
