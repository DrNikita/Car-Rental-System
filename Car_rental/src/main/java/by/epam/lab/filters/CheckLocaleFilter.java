package by.epam.lab.filters;

import java.io.IOException;
import java.util.Locale;

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

import by.epam.lab.property_manager.EntityesManager;

@WebFilter(urlPatterns = { "/*" }, initParams = { @WebInitParam(name = "LOCALE", value = "en") })
public class CheckLocaleFilter extends HttpFilter implements Filter {

	String language;

	public CheckLocaleFilter() {
		super();
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession();

		Locale locale = (Locale) session.getAttribute(EntityesManager.getProperty("locale"));

		if (locale == null) {
			locale = new Locale(language);
			session.setAttribute(EntityesManager.getProperty("locale"), locale);
		}

		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		language = fConfig.getInitParameter("LOCALE");
	}
}
