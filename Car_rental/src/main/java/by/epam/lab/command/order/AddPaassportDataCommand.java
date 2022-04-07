package by.epam.lab.command.order;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.lab.command.ActionCommand;
import by.epam.lab.controller.Router;
import by.epam.lab.entity.User;
import by.epam.lab.exceptions.ServiceLayerException;
import by.epam.lab.mvc.service.IUserService;
import by.epam.lab.mvc.service.impl.UserServiceImpl;
import by.epam.lab.property_manager.ConfigurationManager;
import by.epam.lab.property_manager.EntityesManager;
import by.epam.lab.utils.ServletPaths;

public class AddPaassportDataCommand implements ActionCommand {

	private static final Logger logger = LogManager.getLogger();

	@Override
	public Router execute(HttpServletRequest request) {

		try {
			IUserService userDao = new UserServiceImpl();

			String passpotrNumber = request.getParameter(EntityesManager.getProperty("passport_num"));
			String passportIdNumber = request.getParameter(EntityesManager.getProperty("passport_id_num"));

			int userId = ((User) (request.getSession().getAttribute(EntityesManager.getProperty("user")))).getId();
			userDao.changePassportData(passpotrNumber, passportIdNumber, userId);

			return new Router(ServletPaths.INPUT_DATES_PAGE);

		} catch (ServiceLayerException e) {
			logger.log(Level.ERROR, "Service exception in " + this.getClass().getName() + ": ", e);
			return new Router(ConfigurationManager.getProperty("path.page.error"));

		}
	}
}
