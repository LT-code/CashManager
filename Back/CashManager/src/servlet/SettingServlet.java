package servlet;

import javax.servlet.annotation.WebServlet;

import servlet.function.setting.SettingCreate;
import servlet.function.setting.SettingRemove;
import utils.servlet.Route;

/**
 * Servlet implementation class SettingServlet
 */
@WebServlet("/setting/*")
public class SettingServlet extends Servlet {
	private static final long serialVersionUID = 1L;

	private static final Route[] ROUTES =
		{
			new Route(Route.POST, API_ROUTE + "/setting/create", new SettingCreate(), "", ""),
			new Route(Route.DELETE, API_ROUTE + "/setting/remove", new SettingRemove(), "", "code=?")
		};
	
	@Override
	public Route[] getRoutes() {
		return ROUTES;
	}
}
