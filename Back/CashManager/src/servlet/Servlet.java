package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servlet.function.ServletFunction;
import servlet.function.ServletRequest;
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
    
    public Object[][] get() 	{ return null; }
    public Object[][] post()	{ return null; }
    public Object[][] delete()	{ return null; }
    public Object[][] put() 	{ return null; }
    
	/**
	 * @throws IOException 
	 * @throws ServletException 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		beforRequest("Post", request);
		ServletFunction sf = ServletTools.getServletFunction(request, post());
    	if(sf != null)
    		new ServletRequest(sf, log).executeRequest(request, response);
    	else
    		super.doPost(request, response);
	}
	
	/**
	 * @throws IOException 
	 * @throws ServletException 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    	beforRequest("Get", request);
    	ServletFunction sf = ServletTools.getServletFunction(request, get());
    	if(sf != null)
    		new ServletRequest(sf, log).executeRequest(request, response);
    	else
    		super.doGet(request, response);
	}
	
	/**
	 * @throws IOException 
	 * @throws ServletException 
	 * @see HttpServlet#doPut(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		beforRequest("Put", request);
		ServletFunction sf = ServletTools.getServletFunction(request, put());
    	if(sf != null)
    		new ServletRequest(sf, log).executeRequest(request, response);
    	else
    		super.doPut(request, response);
	}
	
	/**
	 * @throws IOException 
	 * @throws ServletException 
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		beforRequest("Delete", request);
		ServletFunction sf = ServletTools.getServletFunction(request, delete());;
    	if(sf != null)
    		new ServletRequest(sf, log).executeRequest(request, response);
    	else
    		super.doDelete(request, response);
	}
	
	private void beforRequest(String type, HttpServletRequest request) {
		log.addDebug(type + " request " + request.getRequestURI() + " | IP:" + request.getRemoteHost());
	}
}
