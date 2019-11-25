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

import exception.HttpStatusException;
import servlet.function.RouteFunction;
import utils.LogsHandler;
import utils.bdd.DBConnector;
import utils.servlet.HttpStatus;
import utils.servlet.Route;
import utils.servlet.ServletAhtentificated;
import utils.servlet.ServletTools;

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
	public Route[] getRoutes() 	{ return null; }

	/**
	 * @throws IOException
	 * @throws ServletException
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		treatRequest(Route.POST, getRoutes(), request, response);
	}

	/**
	 * @throws IOException
	 * @throws ServletException
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		treatRequest(Route.GET, getRoutes(), request, response);
	}

	/**
	 * @throws IOException
	 * @throws ServletException
	 * @see HttpServlet#doPut(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		treatRequest(Route.PUT, getRoutes(), request, response);
	}

	/**
	 * @throws IOException
	 * @throws ServletException
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		treatRequest(Route.DELETE, getRoutes(), request, response);
	}

	/**
	 * - Check authorisations and erros to finally send the response
	 * @param type for logging the request
	 * @param functionRes for choosing the right route
	 * @param request
	 * @param response
	 * @return whether the user is allowed to use the function or not
	 */
	private void treatRequest(int type, Route[] functionRes, HttpServletRequest request, HttpServletResponse response) {
		log.addDebug(type + " request " + request.getRequestURI() + " | IP:" + request.getRemoteHost());
		RouteFunction servletFunction = ServletTools.getServletFunction(type, request, functionRes);
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		DBConnector db = null;
		
		if (servletFunction != null) {
			try {
				executeRequest(request, response, servletFunction, list, db = new DBConnector(this.log));
			}
			catch(Exception e) {
				if(e instanceof HttpStatusException)
					this.log.addError(e, ((HttpStatusException) e).getHttpStatus());
				else
					this.log.addError(e, HttpStatus.INTERNAL_ERROR);
			}
			db.close();
		}
		else
			log.addError("Not Found", HttpStatus.NOT_FOUND);

		ServletTools.writeResponse(response, list, log, this);
	}
	
	public String getServletRoutes() {
		return ServletTools.getServletRoutes(this);
	}
	
	/*
	 * Get the body and url parametres and execute the function of the route
	 */
	private void executeRequest(HttpServletRequest request, HttpServletResponse response, RouteFunction servletFunction, List<Map<String, Object>> list, DBConnector db) throws Exception {
		JSONObject bodyParams = ServletTools.getJsonBodyParams(request, log);
		JSONObject urlParams = ServletTools.getJsonUrlParams(request, log);
		
		if(bodyParams != null && urlParams != null) {
			if(new ServletAhtentificated(db, request, servletFunction, log).isAllowed()) {
				log.addDebug("Body param receved : " + bodyParams);
				log.addDebug("Url param receved : " + urlParams);
								
				list = servletFunction.execute(	db, 
												bodyParams,
												urlParams,
												list,
												log);
			}
		}
	}
}
