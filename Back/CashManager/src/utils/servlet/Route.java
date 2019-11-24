package utils.servlet;

import servlet.function.RouteFunction;

public class Route {
	public static final int POST = 0;
	public static final int GET = 1;
	public static final int PUT = 2;
	public static final int DELETE = 3;
	public static final String[] STRING_ROUTES = {"POST", "GET", "PUT", "DELETE"};
	
	private int type;
	private String routePath;
	private RouteFunction servletFunction;
	private String bodyParamsRequired;
	private String urlParamsRequired;
	
	public Route(int type, String routePath, RouteFunction servletFunction, String bodyParamsRequired, String urlParamsRequired) {
		this.type = type;
		this.routePath = routePath;
		this.servletFunction = servletFunction;
		this.bodyParamsRequired = bodyParamsRequired;
		this.urlParamsRequired = urlParamsRequired;
	}
	
	public String getBodyParamsRequired() {
		return bodyParamsRequired;
	}

	public void setBodyParamsRequired(String bodyParamsRequired) {
		this.bodyParamsRequired = bodyParamsRequired;
	}
	
	public String getUrlParamsRequired() {
		return urlParamsRequired;
	}

	public void setUrlParamsRequired(String urlParamsRequired) {
		this.urlParamsRequired = urlParamsRequired;
	}

	public String getRoutePath() {
		return routePath;
	}

	public void setRoutePath(String routePath) {
		this.routePath = routePath;
	}

	public RouteFunction getServletFunction() {
		return servletFunction;
	}

	public void setServletFunction(RouteFunction servletFunction) {
		this.servletFunction = servletFunction;
	}
	
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	public String getStringType() {
		return STRING_ROUTES[type];
	}
}
