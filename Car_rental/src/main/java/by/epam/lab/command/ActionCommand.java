package by.epam.lab.command;

import javax.servlet.http.HttpServletRequest;

import by.epam.lab.command.router.Router;

public interface ActionCommand {
	Router execute(HttpServletRequest request);
}
