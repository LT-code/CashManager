package servlet;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpHeaders;

import entities.Machine;
import exception.InvalidNumberReslut;
import services.MachineService;
import servlet.function.ServletFunction;
import servlet.permissions.ServletAdminMachine;
import servlet.permissions.ServletLanbdaMachine;
import utils.DBConnector;
import utils.LogsHandler;

/**
 * Servlet implementation class EngineServlet
 */
public class ServletAhtentificated {
	DBConnector db;
	HttpServletRequest request;
	ServletFunction sf;
	LogsHandler log;
		
	public ServletAhtentificated(DBConnector db, HttpServletRequest request, ServletFunction sf, LogsHandler log) {
		super();
		this.db = db;
		this.request = request;
		this.sf = sf;
		this.log = log;
	}

	public boolean isAllowed() throws SQLException, InvalidNumberReslut {
		if(sf instanceof ServletAdminMachine)
			return checkIfAdmin();
			
		if(sf instanceof ServletLanbdaMachine)
			return checkIfLambda();
		
		return true;
	}
	
	private boolean checkIfAdmin() throws SQLException, InvalidNumberReslut {
		Machine m = getMachine();
		return m == null ? false : m.isAdmin();
	}
	
	private boolean checkIfLambda() throws SQLException, InvalidNumberReslut {
		return getMachine() != null;
	}
	
	private Machine getMachine() throws SQLException, InvalidNumberReslut {
		MachineService m = new MachineService(db, log);
		return m.getByToken(request.getHeader(HttpHeaders.AUTHORIZATION));
	}
}
