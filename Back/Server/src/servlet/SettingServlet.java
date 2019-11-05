package servlet;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;

import entities.Setting;
import exception.FailedDBConnection;
import services.SettingService;
import utils.DBConnector;

/**
 * Servlet implementation class SettingServlet
 */
@WebServlet("/setting")
public class SettingServlet extends Servlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Setting setting = null;
		try {
			DBConnector db = new DBConnector(this.getLogsHandler());
			SettingService service = new SettingService(db, this.getLogsHandler());
			service.add(setting = new Setting());
			db.close();
		} catch (JSONException | FailedDBConnection e) {
			this.getLogsHandler().addError(e);
		}
		
		writeResponse(response, setting);
	}
}
