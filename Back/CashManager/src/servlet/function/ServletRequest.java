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
import utils.HttpStatus;
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
		JSONObject bodyParams = ServletTools.getJsonParams(request, log);
		
		if(bodyParams != null)			
			if((db = new DBConnector(this.log)) != null) {
				try  {
					JSONObject urlParams = new JSONObject(new ObjectMapper().writeValueAsString(request.getParameterMap()));
					log.addDebug("Body param receved : " + bodyParams);
					log.addDebug("Url param receved : " + urlParams);
					
					list = servletFunction.execute(	db, 
													bodyParams,
													urlParams,
													list,
													log);
				}
				catch(Exception e) {
					this.log.addError(e, HttpStatus.INTERNAL_ERROR);
				}
			}
		ServletTools.writeResponse(response, list, log);
	}
}
