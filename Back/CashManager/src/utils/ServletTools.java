package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

    public static JSONObject getJsonParams(HttpServletRequest request, LogsHandler log) {
		StringBuffer jb = new StringBuffer();
		String line = null;	
		JSONObject jsonObject = null;
		try {
			BufferedReader reader = request.getReader();
			while ((line = reader.readLine()) != null)
				jb.append(line);

			String strjson = jb.toString();
			jsonObject = new JSONObject(strjson.equals("") ? "{}" : strjson);
		} catch (JSONException | IOException e) {
			log.addError(e, HttpStatus.BAD_REQUEST);
		}
		
		return jsonObject;
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
