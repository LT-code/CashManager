package utils.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class ResponseHandler {
	public static String serilize(String message, List<Map<String,Object>> list) throws JsonGenerationException, JsonMappingException, IOException {
		String data = new ObjectMapper().writeValueAsString(list);
		/*
		JSONObject json = new JSONObject(data);
		if(json.length() < 2)
			data = data.substring(1, data.length() - 1);*/
		
		return "{" +
				"\"message\":\"" + (message != null ? message.replace("\"", "'") : "") + "\"," +
				"\"data\":" + (data != "" ? data : "[]") + "" +
				"}";
	}
	
	@SuppressWarnings("unchecked")
	public static Map<String, Object> objectToMap(Object obj) {
		return new ObjectMapper().convertValue(obj, Map.class);
	}
}
