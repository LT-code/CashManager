package utils;

import java.util.Date;
import java.util.List;



public class Log {
	public static void info(String message) {
		info(message, null, null);
	}

	public static void info(String clientInfo, String serverInfo, List<String> debugs) {
		message("INFO  | --> ", clientInfo, serverInfo, debugs);
	}

	public static void error(String message) {
		error(message, null, null);
	}

	public static void error(String clientInfo, String serverInfo, List<String> debugs) {
		message("ERROR | /!\\ ", clientInfo, serverInfo, debugs);
	}

	private static void debug(List<String> debugs) {
		if (debugs.size() > 0)
			for (String debug : debugs)
				System.out.println(getHoursDate() + "DEBUG | --> " + debug);
		debugs.removeAll(debugs);
	}
	
	private static void message(String type, String clientInfo, String serverInfo, List<String> debugs) {
		System.out.println("");
		System.out.println("================================================================================================================");
		if(debugs != null) 		debug(debugs);
		if(clientInfo != null)	System.out.println(getHoursDate() + type + clientInfo);
		if(serverInfo != null)	System.out.println(getHoursDate() + type + "ServerOnly : " + serverInfo);
	}

	public static String getHoursDate() {
		return new Date().toString() + " | ";
	}
}
