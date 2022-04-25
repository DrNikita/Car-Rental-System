package by.epam.lab.tag.custom;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import by.epam.lab.property_manager.EntityesManager;

@SuppressWarnings("serial")
public class CommandSubmitButton extends TagSupport {

	private String command;
	private String method;
	private String key;
	private String submitkAction;

	public void setCommand(String command) {
		this.command = command;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public void setSubmitAction(String submitAction) {
		this.submitkAction = submitAction;
	}

	@Override
	public int doStartTag() throws JspException {

		try {

			JspWriter out = pageContext.getOut();

			if (submitkAction != null) {

				if (method != null) {
					out.write("<form method=\"" + method + "\" onsubmit=\"" + submitkAction + "\">");
				} else {
					out.write("<form action=\"/Car_rental/controller\" onsubmit=\"" + submitkAction + "\">");
				}

			} else {

				if (method != null) {
					out.write("<form action=\"/Car_rental/controller\" method=\"" + method + "\">");
				} else {
					out.write("<form action=\"/Car_rental/controller\">");
				}
			}

			out.write("<input type=\"hidden\" name=\"command\" value=\"" + command + "\">");

		} catch (IOException e) {
			throw new JspException(e.getMessage());
		}

		return EVAL_BODY_INCLUDE;
	}

	@Override
	public int doEndTag() throws JspException {

		try {

			Locale locale = (Locale) pageContext.getSession().getAttribute(EntityesManager.getProperty("locale"));

			ResourceBundle resourceBundle = ResourceBundle.getBundle("properties.resourses", locale);

			JspWriter out = pageContext.getOut();

			out.write("<input type=\"submit\" value=\"" + resourceBundle.getString(key) + "\">");
			out.write("</form>");

		} catch (IOException e) {
			throw new JspException(e.getMessage());
		}

		return EVAL_PAGE;
	}
}
