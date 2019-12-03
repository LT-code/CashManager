package servlet;

import javax.servlet.annotation.WebServlet;

import servlet.function.machine.MachineConnect;
import servlet.function.machine.MachineCreate;
import servlet.function.machine.MachineDelete;
import utils.servlet.Route;

/**
 * Servlet implementation class MachineServlet
 */
@WebServlet("/machine/*")
public class MachineServlet extends Servlet {
	private static final long serialVersionUID = 1L;
	
	private static final Route[] ROUTES =
		{
			new Route(Route.POST, API_ROUTE + "/machine/create", new MachineCreate(), "String idMachine, long idSetting, boolean isAdmin, String password", "", "Create a machine."),
			new Route(Route.POST, API_ROUTE + "/machine/connect", new MachineConnect(), "String idMachine, String password", "", "Connect the machine to the server for getting a token."),
			new Route(Route.DELETE, API_ROUTE + "/machine/remove", new MachineDelete(), "", "idMachine=?", "Remove a machine.")
		};
	
	@Override
	public Route[] getRoutes() {
		return ROUTES;
	}

	@Override
	public String getName() {
		return "Machine";
	}
}