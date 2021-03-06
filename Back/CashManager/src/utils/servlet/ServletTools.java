package utils.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;

import servlet.Servlet;
import servlet.function.RouteFunction;
import utils.LogsHandler;

public class ServletTools {
	public static RouteFunction getServletFunction(int type, HttpServletRequest request, Route[] listRoutes) {
		if(listRoutes != null)
			for(int i = 0; i < listRoutes.length; i++)
				if(listRoutes[i].getType() == type)
					if(listRoutes[i].getRoutePath().equals(request.getRequestURI()))
						return (RouteFunction) listRoutes[i].getServletFunction();
		return null;
	}

    public static JSONObject getJsonBodyParams(HttpServletRequest request, LogsHandler log) throws IOException {
		StringBuffer jb = new StringBuffer();
		String line = null;
	
		BufferedReader reader = request.getReader();
		while ((line = reader.readLine()) != null)
			jb.append(line);

		String strjson = jb.toString();		
		return new JSONObject(strjson.equals("") ? "{}" : strjson);
    }
    
    public static JSONObject getJsonUrlParams(HttpServletRequest request, LogsHandler log) throws JsonGenerationException, JsonMappingException, JSONException, IOException {
    	return new JSONObject(new ObjectMapper().writeValueAsString(request.getParameterMap()));
    }
    
    public static void writeResponse(HttpServletResponse response, List<Map<String, Object>> list, LogsHandler log, Servlet servlet) {
    	response.setHeader("Content-type", "json/application");
		
    	PrintWriter writer;
		try {
			
			response.setStatus((log.getHttpStatus()));
			writer = response.getWriter();
			
			if(log.getHttpStatus() == HttpStatus.NOT_FOUND) {
				response.setHeader("Content-type", "text/html; charset=iso-8859-1");
				writer.println(servlet.getServletRoutes());
			}
			else {
				String message = log.getPrincipalMessages();
				if(message == null || message == "")
					message = log.getLastInfo();
				writer.println(ResponseHandler.serilize(message, list));
			}
			
			writer.close();
		} catch (IOException e) {
			log.addError(e, HttpStatus.INTERNAL_ERROR);
		}
		
		log.displayLogs();
    }

    public static String getServletRoutes(Servlet servlet) {
		String res = "<div>";
		
		res += "<h3>" + servlet.getName() + "</h3>";
		for(Route route : servlet.getRoutes())
			res += route.toString();			
		
		return res + "</div>";
	}
}
