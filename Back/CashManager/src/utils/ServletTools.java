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

public class ServletTools {
	public static String getCurrentUrlPath(HttpServletRequest request) {
		return request.getRequestURI();
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
			log.addInfo("Params sent : " + jsonObject.toString());
		} catch (JSONException | IOException e) {
			log.addError(e);
		}
		
		return jsonObject;
    }
    
    public static void writeResponse(HttpServletResponse response, List<Map<String, Object>> list, LogsHandler log) {
    	response.setHeader("Content-type", "json/application");
		
    	PrintWriter writer;
		try {
			writer = response.getWriter();
			writer.println(ResponseHandler.serilize(
					log.isValid(),
					log.getPrincipalMessages(),
					list
				));
			writer.close();
		} catch (IOException e) {
			log.addError(e);
		}
		
		log.displayLogs();
    }

}
