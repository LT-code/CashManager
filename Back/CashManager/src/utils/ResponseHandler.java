package utils;

import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;

public class ResponseHandler {
	public static String serilize(String message, List<Map<String,Object>> list) {
		String data = "";
		if(list != null) {
			if(list.size() > 1)
				data += "[";
			for(Map<String, Object> map : list)	{
				data += "{";
				if(map != null) {
					for(Map.Entry<String, Object> value : map.entrySet())
					data += "\"" + value.getKey() + "\"" + ":" +
							(value.getValue() instanceof String ? "\"" : "") +
							value.getValue() +
							(value.getValue() instanceof String ? "\"" : "") +
							",";
					data = data.substring(0, data.length() - 1);
				}
				
				data += "}" + ",";
			}
			if(data != "")
				data = data.substring(0, data.length() - 1);
			if(list.size() > 1)
				data += "]";
		}
		
		return "{" +
				"\"message\":\"" + message + "\"," +
				"\"data\":" + (data != "" ? data : "{}") + "" +
				"}";
	}
	
	@SuppressWarnings("unchecked")
	public static Map<String, Object> objectToMap(Object obj) {
		return new ObjectMapper().convertValue(obj, Map.class);
	}
}
