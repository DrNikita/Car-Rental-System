package by.epam.lab.filters;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epam.lab.command.CommandEnum;
import by.epam.lab.entity.User.Role;
import by.epam.lab.property_manager.ConfigurationManager;
import by.epam.lab.property_manager.EntityesManager;

@WebFilter(urlPatterns = { "/controller" }, servletNames = { "ServletSecurity" })
public class ServletSecurityFilter extends HttpFilter implements Filter {

	public ServletSecurityFilter() {
		super();
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponce = (HttpServletResponse) response;
		HttpSession session = httpRequest.getSession();

		Role role = (Role) session.getAttribute(EntityesManager.getProperty("role"));
		String command = request.getParameter(EntityesManager.getProperty("command"));

		if (command == null) {

			RequestDispatcher dispatcher = request.getServletContext()
					.getRequestDispatcher(ConfigurationManager.getProperty("path.page.login"));
			dispatcher.forward(httpRequest, httpResponce);
			return;

		} else if (role == Role.GUEST && !command.equalsIgnoreCase(CommandEnum.TO_LOGIN_PAGE.toString())
				&& !command.equalsIgnoreCase(CommandEnum.TO_REGISTRATION_PAGE.toString())
				&& !command.equalsIgnoreCase(CommandEnum.LOGIN.toString())
				&& !command.equalsIgnoreCase(CommandEnum.USER_REGISTRATION.toString())
				&& !command.equalsIgnoreCase(CommandEnum.SET_LOCALE.toString())) {

			RequestDispatcher dispatcher = request.getServletContext()
					.getRequestDispatcher(ConfigurationManager.getProperty("path.page.login"));
			dispatcher.forward(httpRequest, httpResponce);

			return;
		}

		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}
}
