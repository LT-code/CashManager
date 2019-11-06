package servlet;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import entities.Machine;
import exception.FailedDBConnection;
import services.MachineService;
import utils.DBConnector;

/**
 * Servlet implementation class MachineServlet
 */
@WebServlet("/machine")
public class MachineServlet extends Servlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Machine machine = null;
		
		try {
			JSONObject params = getJsonParams(request);
			DBConnector db = new DBConnector(this.getLogsHandler());
			MachineService articleService = new MachineService(db, this.getLogsHandler());
			articleService.add(
					machine = new Machine(
						params.getString("idMachine"), 
						params.getLong("idSetting"),
						params.getBoolean("isAdmin"),
						params.getString("password")
					));
			
			db.close();
		} catch (JSONException | FailedDBConnection e) {
			this.getLogsHandler().addError(e);
		}

		writeResponse(response, machine);
	}
}
