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

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebFilter(urlPatterns = { "/*" }, dispatcherTypes = { DispatcherType.FORWARD, DispatcherType.REQUEST,
		DispatcherType.INCLUDE }, initParams = {
				@WebInitParam(name = "encoding", value = "UTF-8", description = "Encoding Param") })
public class EncodingFilter extends HttpFilter implements Filter {

	private String code;

	public EncodingFilter() {
		super();
	}

	public void destroy() {
		code = null;
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		String codeRequest = request.getCharacterEncoding();

		if (code != null && !code.equalsIgnoreCase(codeRequest)) {
			request.setCharacterEncoding(code);
			response.setCharacterEncoding(code);
		}

		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		code = fConfig.getInitParameter("encoding");
	}
}
