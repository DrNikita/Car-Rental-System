package by.epam.lab.command;

import javax.servlet.http.HttpServletRequest;

import by.epam.lab.controller.Router;

public interface ActionCommand {
	Router execute(HttpServletRequest request);
}
