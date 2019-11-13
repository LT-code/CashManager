package servlet;

import javax.servlet.annotation.WebServlet;

import servlet.function.machine.MachineConnect;
import servlet.function.machine.MachineCreate;

/**
 * Servlet implementation class MachineServlet
 */
@WebServlet("/machine/*")
public class MachineServlet extends Servlet {
	private static final long serialVersionUID = 1L;
	
	private static final Object[][] ROUTES_POST =
		{
			{ API_ROUTE + "/machine/create", new MachineCreate() },
			{ API_ROUTE + "/machine/connect", new MachineConnect() }
		};
	
	@Override
	public Object[][] post() {
		return ROUTES_POST;
	}
}