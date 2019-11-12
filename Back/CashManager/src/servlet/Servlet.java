package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servlet.function.ServletFunction;
import servlet.function.ServletRequest;
import utils.LogsHandler;


/**
 * Servlet implementation class EngineServlet
 */
public abstract class Servlet extends HttpServlet {
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
    
    public ServletFunction get(HttpServletRequest request) 	{ return null; }
    public ServletFunction post(HttpServletRequest request) 	{ return null; }
    public ServletFunction delete(HttpServletRequest request){ return null; }
    public ServletFunction put(HttpServletRequest request) 	{ return null; }
    
	/**
	 * @throws IOException 
	 * @throws ServletException 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		beforRequest("Post", request);
		ServletFunction sf = post(request);
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
    	ServletFunction sf = get(request);
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
		ServletFunction sf = put(request);
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
		ServletFunction sf = delete(request);
    	if(sf != null)
    		new ServletRequest(sf, log).executeRequest(request, response);
    	else
    		super.doDelete(request, response);
	}
	
	private void beforRequest(String type, HttpServletRequest request) {
		log.addDebug(type + " request " + request.getRequestURI() + " | IP:" + request.getRemoteHost());
	}
}
