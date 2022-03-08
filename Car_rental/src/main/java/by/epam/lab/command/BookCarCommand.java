package by.epam.lab.command;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import by.epam.lab.controller.Router;
import by.epam.lab.utils.EntityesManager;
import by.epam.lab.utils.ServletPaths;

public class BookCarCommand implements ActionCommand {

	@Override
	public Router execute(HttpServletRequest request) {

		Router router = new Router();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		try {
			Date startDate = format.parse(request.getParameter(EntityesManager.getProperty("start_date")));
			Date endDate = format.parse(request.getParameter(EntityesManager.getProperty("end_date")));

			router.setPagePath(ServletPaths.MAIN);

		} catch (ParseException e) {
			e.printStackTrace();
		}

		return router;
	}

}
