package servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import servlet.function.ServletFunction;
import servlet.function.machine.MachineConnect;
import servlet.function.machine.MachineCreate;
import utils.ServletTools;

/**
 * Servlet implementation class MachineServlet
 */
@WebServlet("/machine/*")
public class MachineServlet extends Servlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	public ServletFunction post(HttpServletRequest request) {
		return ;
		
		switch(ServletTools.getCurrentUrlPath(request)) {
			case "/cashmanager/machine/create":
				return new MachineCreate();
			case "/cashmanager/machine/connect":
				return new MachineConnect();
		}			
		return null;
	}
}