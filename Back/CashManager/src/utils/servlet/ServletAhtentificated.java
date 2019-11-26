package utils.servlet;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpHeaders;

import entities.Machine;
import exception.AccessForbidden;
import exception.AccessUnauthorized;
import exception.InvalidNumberReslut;
import services.MachineService;
import servlet.function.RouteFunction;
import servlet.permissions.ServletAdminMachine;
import servlet.permissions.ServletLanbdaMachine;
import utils.LogsHandler;
import utils.bdd.DBConnector;

/**
 * Servlet implementation class EngineServlet
 */
public class ServletAhtentificated {
	DBConnector db;
	HttpServletRequest request;
	RouteFunction sf;
	LogsHandler log;
		
	public ServletAhtentificated(DBConnector db, HttpServletRequest request, RouteFunction sf, LogsHandler log) {
		super();
		this.db = db;
		this.request = request;
		this.sf = sf;
		this.log = log;
	}

	public boolean isAllowed() throws Exception {
		if(sf instanceof ServletAdminMachine)
			checkIfAdmin();
			
		if(sf instanceof ServletLanbdaMachine)
			checkIfLambda();
		
		return true;
	}
	
	private void checkIfAdmin() throws Exception {
		Machine m = getMachine();
		if(m == null ? true : !m.isAdmin())
			throw new AccessForbidden();
	}
	
	private void checkIfLambda() throws Exception {
		if(getMachine() == null)
			throw new AccessForbidden();
	}
	
	private Machine getMachine() throws Exception {
		MachineService m = new MachineService(db, log);
		try {
			String token = request.getHeader(HttpHeaders.AUTHORIZATION);
			if(token == null)
				throw new AccessUnauthorized();
			
			return m.getByToken(token);
		}
		catch(Exception e) {
			if(e instanceof InvalidNumberReslut)
				throw new AccessForbidden();
			throw e;
		}
	}
}
