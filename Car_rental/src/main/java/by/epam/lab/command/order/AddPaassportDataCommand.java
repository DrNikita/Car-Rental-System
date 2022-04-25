package by.epam.lab.command.order;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.lab.command.ActionCommand;
import by.epam.lab.command.router.ErrorRouter;
import by.epam.lab.command.router.ForwardRouter;
import by.epam.lab.command.router.Router;
import by.epam.lab.entity.User;
import by.epam.lab.exceptions.ServiceLayerException;
import by.epam.lab.mvc.service.IUserService;
import by.epam.lab.mvc.service.impl.UserServiceImpl;
import by.epam.lab.property_manager.ConfigurationManager;
import by.epam.lab.property_manager.EntityesManager;

public class AddPaassportDataCommand implements ActionCommand {

	private static final Logger logger = LogManager.getLogger();

	@Override
	public Router execute(HttpServletRequest request) {

		try {
			IUserService userDao = new UserServiceImpl();

			String passpotrNumber = request.getParameter(EntityesManager.getProperty("passport_number"));
			String passportIdNumber = request.getParameter(EntityesManager.getProperty("passport_id_number"));

			User user = (User) (request.getSession().getAttribute(EntityesManager.getProperty("user")));

			if (user != null) {

				userDao.changePassportData(passpotrNumber, passportIdNumber, user.getId());
				return new ForwardRouter(ConfigurationManager.getProperty("path.page.input_dates"));

			} else {
				logger.log(Level.INFO, "User in session wasn't found.");
				return new ForwardRouter();
			}

		} catch (ServiceLayerException e) {
			logger.log(Level.ERROR, "Service exception in " + this.getClass().getName() + ": ", e);
			return new ErrorRouter(e);

		}
	}
}
