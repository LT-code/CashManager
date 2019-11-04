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

import entities.Article;
import entities.EntityClass;
import utils.ErrorsHandler;
import utils.ResponseHandler;


/**
 * Servlet implementation class EngineServlet
 */
abstract class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ErrorsHandler errhandler;

	/**
     * @see HttpServlet#HttpServlet()
     */
    public Servlet() {
        super();
        errhandler = new ErrorsHandler();
        errhandler.addDebug("servlet creation");
    }
    
    public ErrorsHandler getErrorsHandler() {
    	return errhandler;
    }
    
    public JSONObject getJsonParams(HttpServletRequest request) {
		StringBuffer jb = new StringBuffer();
		String line = null;
		JSONObject jsonObject = null;
		try {
			BufferedReader reader = request.getReader();
			while ((line = reader.readLine()) != null)
				jb.append(line);
		} catch (Exception e) {
			System.out.println("hello " + ErrorsHandler.getMessageError(e));
		}

		try {
			jsonObject = new JSONObject(jb.toString());
		} catch (JSONException e) {
			System.out.println("hello " + ErrorsHandler.getMessageError(e));
			// crash and burn
			// throw new IOException("Error parsing JSON request string");
		}
		return jsonObject;
    }
    
    public Object getObjectByJson(String obj, Class<Article> class1) throws IOException {
        return new ObjectMapper().readValue(obj, class1);
    }
    
    public void writeResponse(HttpServletResponse response, EntityClass entity) throws IOException {
    	response.setHeader("Content-type", "json/application");
		PrintWriter writer = response.getWriter();
		writer.println(ResponseHandler.serilize(
				this.getErrorsHandler().isValid(),
				this.getErrorsHandler().getMessage(),
				new ObjectMapper().writeValueAsString(entity)
			));
		writer.close();
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
		errhandler.addDebug(type + " request " + request.getRequestURI() + " | IP:" + request.getRemoteHost());
	}
}
