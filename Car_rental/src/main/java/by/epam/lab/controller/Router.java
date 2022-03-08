package by.epam.lab.controller;

public class Router {

	private String pagePath;
	private RouteType type;

	public enum RouteType {
		FORWARD, REDIRECT;
	}

	public Router() {
		this.type = RouteType.FORWARD;
	}

	public Router(String pagePath) {
		this.pagePath = pagePath;
		this.type = RouteType.FORWARD;
	}

	public Router(String pagePath, RouteType type) {
		this.pagePath = pagePath;
		this.type = type;
	}

	public String getPagePath() {
		return pagePath;
	}

	public void setPagePath(String pagePath) {
		this.pagePath = pagePath;
	}

	public RouteType getType() {
		return type;
	}

	public void setType(RouteType type) {
		this.type = type;
	}
}
