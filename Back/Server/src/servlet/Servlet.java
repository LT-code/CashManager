package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;

import entities.EntityClass;
import utils.LogsHandler;
import utils.ResponseHandler;


/**
 * Servlet implementation class EngineServlet
 */
abstract class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LogsHandler log;

	/**
     * @see HttpServlet#HttpServlet()
     */
    public Servlet() {
        super();
        log = new LogsHandler();
        log.addDebug("servlet creation");
    }
    
    public LogsHandler getLogsHandler() {
    	return log;
    }
    
    public JSONObject getJsonParams(HttpServletRequest request) {
		StringBuffer jb = new StringBuffer();
		String line = null;
		JSONObject jsonObject = null;
		try {
			BufferedReader reader = request.getReader();
			while ((line = reader.readLine()) != null)
				jb.append(line);

			jsonObject = new JSONObject(jb.toString());
			this.getLogsHandler().addInfo("Params sent : " + jsonObject.toString());
		} catch (JSONException | IOException e) {
			this.getLogsHandler().addError(e);
		}
		
		return jsonObject;
    }
    
    public void writeResponse(HttpServletResponse response, EntityClass entity) throws IOException {
    	response.setHeader("Content-type", "json/application");
		PrintWriter writer = response.getWriter();
		writer.println(ResponseHandler.serilize(
				this.getLogsHandler().isValid(),
				this.getLogsHandler().getPrincipalMessages(),
				new ObjectMapper().writeValueAsString(entity)
			));
		writer.close();
		log.displayLogs();
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		infoRequestlog("Get", request);
		super.doGet(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		infoRequestlog("Post", request);
		super.doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		infoRequestlog("Put", request);
		super.doPut(request,response);
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		infoRequestlog("Delete", request);
		super.doDelete(request,response);
	}
	
	private void infoRequestlog(String type, HttpServletRequest request) {
		log.addDebug(type + " request " + request.getRequestURI() + " | IP:" + request.getRemoteHost());
	}
}
