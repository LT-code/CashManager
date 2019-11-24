package servlet;

import javax.servlet.annotation.WebServlet;

import servlet.function.setting.SettingCreate;
import utils.servlet.Route;

/**
 * Servlet implementation class SettingServlet
 */
@WebServlet("/setting/*")
public class SettingServlet extends Servlet {
	private static final long serialVersionUID = 1L;

	private static final Route[] ROUTES =
		{
			new Route(Route.POST, API_ROUTE + "/setting/create", new SettingCreate(), "", "")
		};
	
	@Override
	public Route[] getRoutes() {
		return ROUTES;
	}
}
