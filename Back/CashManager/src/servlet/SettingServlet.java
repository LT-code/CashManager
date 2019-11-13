package servlet;

import javax.servlet.annotation.WebServlet;

import servlet.function.setting.SettingCreate;

/**
 * Servlet implementation class SettingServlet
 */
@WebServlet("/setting/*")
public class SettingServlet extends Servlet {
	private static final long serialVersionUID = 1L;
	
	private static final Object[][] ROUTES_POST =
		{
			{ API_ROUTE + "/setting/create", new SettingCreate() }
		};
	
	@Override
	public Object[][] post() {
		return ROUTES_POST;
	}
}
