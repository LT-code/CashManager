package servlet;

import javax.servlet.annotation.WebServlet;

import servlet.function.setting.SettingCreate;
import servlet.function.setting.SettingDelete;
import utils.servlet.Route;

/**
 * Servlet implementation class SettingServlet
 */
@WebServlet("/setting/*")
public class SettingServlet extends Servlet {
	private static final long serialVersionUID = 1L;

	private static final Route[] ROUTES =
		{
			new Route(Route.POST, API_ROUTE + "/setting/create", new SettingCreate(), "", "", "Create a setting."),
			new Route(Route.DELETE, API_ROUTE + "/setting/remove", new SettingDelete(), "", "code=?", "Remove a setting.")
		};
	
	@Override
	public Route[] getRoutes() {
		return ROUTES;
	}

	@Override
	public String getName() {
		return "Setting";
	}
}
