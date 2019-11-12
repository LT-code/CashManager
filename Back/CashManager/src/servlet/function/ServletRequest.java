package servlet.function;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONObject;

import utils.DBConnector;
import utils.LogsHandler;
import utils.ServletTools;

/**
 * @author retzs64
 *
 */
public class ServletRequest {
	private LogsHandler log;
	private ServletFunction servletFunction;

	/**
     * @see HttpServlet#HttpServlet()
     */
    public ServletRequest(ServletFunction servletFunction, LogsHandler log) {
    	this.servletFunction = servletFunction;
    	this.log = log;
    }
    
	public void executeRequest(HttpServletRequest request, HttpServletResponse response) {
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		DBConnector db = null;	
		JSONObject params = ServletTools.getJsonParams(request, log);
		
		if(params != null)			
			if((db = new DBConnector(this.log)) != null) {
				try  {
					list = servletFunction.execute(	db, 
													params,
													new JSONObject(new ObjectMapper().writeValueAsString(request.getParameterMap())),
													list,
													log);
				}
				catch(Exception e) {
					this.log.addError(e);
				}
			}
		ServletTools.writeResponse(response, list, log);
	}
}
