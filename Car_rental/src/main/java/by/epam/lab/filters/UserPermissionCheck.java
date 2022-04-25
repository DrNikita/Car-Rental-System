package by.epam.lab.filters;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.epam.lab.command.CommandEnum;
import by.epam.lab.entity.User.Role;
import by.epam.lab.property_manager.EntityesManager;

//@WebFilter(urlPatterns = { ("/controller") })
public class UserPermissionCheck extends HttpFilter implements Filter {

	private static final Set<String> ALLOWED_PATH_GUEST = new HashSet<>(
			Arrays.asList("/index.jsp", "/jsp/about.jsp", "/jsp/contact.jsp", "/jsp/main.jsp", "/jsp/services.jsp",
					"/jsp/signin.jsp", "/jsp/signup.jsp", "/jsp/error.jsp"));

	public UserPermissionCheck() {
		super();
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession();

		Role role = (Role) httpRequest.getSession().getAttribute(EntityesManager.getProperty("role"));
		String command = httpRequest.getParameter(EntityesManager.getProperty("command"));

		if (role == Role.USER) {
			if (command.equalsIgnoreCase(CommandEnum.TO_LOGIN_PAGE.toString())
					|| command.equalsIgnoreCase(CommandEnum.TO_LOGIN_PAGE.toString())
					|| command.equalsIgnoreCase(CommandEnum.TO_LOGIN_PAGE.toString())
					|| command.equalsIgnoreCase(CommandEnum.TO_LOGIN_PAGE.toString())
					|| command.equalsIgnoreCase(CommandEnum.TO_LOGIN_PAGE.toString())
					|| command.equalsIgnoreCase(CommandEnum.TO_LOGIN_PAGE.toString())
					|| command.equalsIgnoreCase(CommandEnum.TO_LOGIN_PAGE.toString())
					|| command.equalsIgnoreCase(CommandEnum.TO_LOGIN_PAGE.toString())
					|| command.equalsIgnoreCase(CommandEnum.TO_LOGIN_PAGE.toString())
					|| command.equalsIgnoreCase(CommandEnum.TO_LOGIN_PAGE.toString())
					|| command.equalsIgnoreCase(CommandEnum.TO_LOGIN_PAGE.toString())) {

			}
		}

		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}
}
