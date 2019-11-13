package utils;

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

import servlet.function.ServletFunction;

public class ServletTools {
	public static ServletFunction getServletFunction(HttpServletRequest request, Object[][] listRoutes) {
		if(listRoutes != null)
			for(int i = 0; i < listRoutes.length; i++)
				if(listRoutes[i][0].equals(request.getRequestURI()))
					return (ServletFunction) listRoutes[i][1];
				
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
    
    public static void writeResponse(HttpServletResponse response, List<Map<String, Object>> list, LogsHandler log) {
    	response.setHeader("Content-type", "json/application");
		
    	PrintWriter writer;
		try {
			response.setStatus((log.isValid() ? 200 : 400));
			writer = response.getWriter();
			writer.println(ResponseHandler.serilize(log.getPrincipalMessages(),	list));
			
			writer.close();
		} catch (IOException e) {
			log.addError(e, HttpStatus.INTERNAL_ERROR);
		}
		
		log.displayLogs();
    }

}
