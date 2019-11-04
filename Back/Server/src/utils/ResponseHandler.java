package utils;

public class ResponseHandler {
	public static String serilize(boolean status, String message, String data) {
	
		return "{" +
				"\"status\":\"" + status + "\"," +
				"\"message\":\"" + message + "\"," +
				"\"data\":{" + data + "}" +
				"}";
	}
}
