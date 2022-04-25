package by.epam.lab.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.lab.command.CommandEnum;
import by.epam.lab.entity.User;
import by.epam.lab.property_manager.ConfigurationManager;
import by.epam.lab.property_manager.EntityesManager;

@WebFilter(urlPatterns = { "/controller" })
public class LogginFilter extends HttpFilter implements Filter {

	private static final Logger logger = LogManager.getLogger();

	public LogginFilter() {
		super();
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession();
		User user = (User) session.getAttribute(EntityesManager.getProperty("user"));
		String command = httpRequest.getParameter(EntityesManager.getProperty("command"));

		if (user == null && command != null && !command.equalsIgnoreCase(CommandEnum.TO_LOGIN_PAGE.toString())
				&& !command.equalsIgnoreCase(CommandEnum.TO_REGISTRATION_PAGE.toString())
				&& !command.equalsIgnoreCase(CommandEnum.LOGIN.toString())
				&& !command.equalsIgnoreCase(CommandEnum.USER_REGISTRATION.toString())
				&& !command.equalsIgnoreCase(CommandEnum.SET_LOCALE.toString())) {

			session.invalidate();
			logger.log(Level.INFO, "user not in the session, session was invalidated");

			HttpServletResponse httpResponse = (HttpServletResponse) response;
			httpResponse.sendRedirect(
					request.getServletContext().getContextPath() + ConfigurationManager.getProperty("path.page.login"));
			return;
		}

		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}
}
