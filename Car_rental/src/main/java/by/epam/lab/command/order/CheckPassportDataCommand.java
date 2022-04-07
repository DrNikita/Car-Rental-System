//package by.epam.lab.command.order;
//
//import javax.servlet.http.HttpServletRequest;
//
//import by.epam.lab.command.ActionCommand;
//import by.epam.lab.controller.Router;
//import by.epam.lab.entity.User;
//import by.epam.lab.property_manager.EntityesManager;
//import by.epam.lab.utils.ServletPaths;
//
//public class CheckPassportDataCommand implements ActionCommand {
//
//	@Override
//	public Router execute(HttpServletRequest request) {
//
//		request.getSession().setAttribute(EntityesManager.getProperty("car_id"),
//				request.getParameter(EntityesManager.getProperty("car_id")));
//
//		Router router = new Router();
//
//		User user = (User) request.getSession().getAttribute(EntityesManager.getProperty("user"));
//
//		if (user.isPassportDataExists()) {
//			router.setPagePath(ServletPaths.INPUT_DATES_PAGE);
//		} else {
//			router.setPagePath(ServletPaths.INPUT_PASSPORT_DATA_PAGE);
//		}
//
//		return router;
//	}
//}
