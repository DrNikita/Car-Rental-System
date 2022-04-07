package by.epam.lab.filters;

import java.io.IOException;
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

import by.epam.lab.entity.User.Role;
import by.epam.lab.property_manager.ConfigurationManager;
import by.epam.lab.property_manager.EntityesManager;

@WebFilter(urlPatterns = { "/controller" }, servletNames = { "ServletSecurity" })
public class GuestUserFilter extends HttpFilter implements Filter {

	public GuestUserFilter() {
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

		if (role == null) {
			role = Role.GUEST;
			session.setAttribute(EntityesManager.getProperty("role"), role);

			RequestDispatcher dispatcher = request.getServletContext()
					.getRequestDispatcher(ConfigurationManager.getProperty("path.page.guest"));
			dispatcher.forward(httpRequest, httpResponce);

			return;
		}

		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}
}
