package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import servlet.function.ServletFunction;
import utils.DBConnector;
import utils.HttpStatus;
import utils.LogsHandler;
import utils.ServletTools;

/**
 * Servlet implementation class EngineServlet
 */
public abstract class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LogsHandler log;
	public static final String API_ROUTE = "/cashmanager";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Servlet() {
		super();
		log = new LogsHandler();
		log.addDebug("servlet creation");
	}

	/*
	 *  Those functions are made to be overrided with the servlet's routes
	 */
	public Object[][] get() 	{ return null; }
	public Object[][] post() 	{ return null; }
	public Object[][] delete() 	{ return null; }
	public Object[][] put() 	{ return null; }

	/**
	 * @throws IOException
	 * @throws ServletException
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		treatRequest("Post", post(), request, response);
	}

	/**
	 * @throws IOException
	 * @throws ServletException
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		treatRequest("Get", get(), request, response);
	}

	/**
	 * @throws IOException
	 * @throws ServletException
	 * @see HttpServlet#doPut(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		treatRequest("Put", put(), request, response);
	}

	/**
	 * @throws IOException
	 * @throws ServletException
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		treatRequest("Delete", delete(), request, response);
	}

	/**
	 * 
	 * @param type for logging the request
	 * @param functionRes for choosing the right route
	 * @param request
	 * @param response
	 * @return whether the user is allowed to use the function or not
	 */
	private void treatRequest(String type, Object[][] functionRes, HttpServletRequest request, HttpServletResponse response) {
		log.addDebug(type + " request " + request.getRequestURI() + " | IP:" + request.getRemoteHost());
		ServletFunction sf = ServletTools.getServletFunction(request, functionRes);
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		
		if (sf != null) {
			DBConnector db = null;	
			JSONObject bodyParams = ServletTools.getJsonBodyParams(request, log);
			JSONObject urlParams = ServletTools.getJsonUrlParams(request, log);
			
			if(bodyParams != null && urlParams != null)
				if((db = new DBConnector(this.log)).isConnected())
					if(new ServletAhtentificated(db, request, sf, log).isAllowed())
						executeRequest(request, response, sf, db, bodyParams, urlParams, list);
		}
		else
			log.addError("Not Found", HttpStatus.NOT_FOUND);

		ServletTools.writeResponse(response, list, log);
	}
	
	/*
	 * 
	 */
	private void executeRequest(HttpServletRequest request, HttpServletResponse response, ServletFunction servletFunction, DBConnector db, JSONObject bodyParams, JSONObject urlParams, List<Map<String, Object>> list) {
		log.addDebug("Body param receved : " + bodyParams);
		log.addDebug("Url param receved : " + urlParams);
		
		try  {					
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
}
