package by.epam.lab.command.router;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Router {
	void send(HttpServletRequest request, HttpServletResponse response);
}
