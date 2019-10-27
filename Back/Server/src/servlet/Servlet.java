package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.utils.CM_Log;


/**
 * Servlet implementation class EngineServlet
 */
abstract class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
     * @see HttpServlet#HttpServlet()
     */
    public Servlet() {
        super();
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
		CM_Log.info(type + " request " + request.getRequestURI() + " | IP:" + request.getRemoteHost());
	}
}
