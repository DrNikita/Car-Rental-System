package by.epam.lab.filters;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.lab.property_manager.ConfigurationManager;
import by.epam.lab.property_manager.EntityesManager;

//@WebFilter(urlPatterns = { "/controller" }, initParams = {
//		@WebInitParam(name = "get", value = "GET", description = "GET method type"),
//		@WebInitParam(name = "post", value = "POST", description = "POST method type") })
public class RequestMethodCheckFilter extends HttpFilter implements Filter {

	private String get;
	private String post;
	private static final Logger logger = LogManager.getLogger();

	public RequestMethodCheckFilter() {
		super();
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		String method = httpRequest.getMethod();

		if (method != null && method.equalsIgnoreCase(post)) {
			logger.log(Level.INFO, "Method filter worked");

			httpRequest.getRequestDispatcher(ConfigurationManager.getProperty("path.command.logout"))
					.forward(httpRequest, httpResponse);
		}

		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		get = fConfig.getInitParameter("get");
		post = fConfig.getInitParameter("post");
	}
}
