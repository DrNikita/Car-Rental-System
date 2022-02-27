package by.epam.lab.authorization;

import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

public class SessionRequestContent {
	private HashMap<String, Object> requestAttributes;
	private HashMap<String, String[]> requestParameters;
	private HashMap<String, Object> sessionAttributes;

	public SessionRequestContent() {
	}

	public void extractValues(HttpServletRequest request) {
		Enumeration attributeNames = request.getAttributeNames();
		while (attributeNames.hasMoreElements()) {
			String current = (String) attributeNames.nextElement();
			requestAttributes.put(current, request.getAttribute(current));
		}
	}

	public void insertAttributes(HttpServletRequest request) {
	}

	public HashMap<String, Object> getRequestAttributes() {
		return requestAttributes;
	}

	public void setRequestAttributes(HashMap<String, Object> requestAttributes) {
		this.requestAttributes = requestAttributes;
	}

	public HashMap<String, String[]> getRequestParameters() {
		return requestParameters;
	}

	public void setRequestParameters(HashMap<String, String[]> requestParameters) {
		this.requestParameters = requestParameters;
	}

	public HashMap<String, Object> getSessionAttributes() {
		return sessionAttributes;
	}

	public void setSessionAttributes(HashMap<String, Object> sessionAttributes) {
		this.sessionAttributes = sessionAttributes;
	}
}
