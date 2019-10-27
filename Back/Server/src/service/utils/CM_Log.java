package service.utils;

public class CM_Log {
    // Operations
    public static void info(String message) {
    	CM_Log.message("INFO", message);
    }
    
    public static void debug(String message) {
    	CM_Log.message("DEBUG", message);
    }
    
    private static void message(String type, String message) {
    	System.out.println(type + " --> " + message);
    }
    
    //################################################
    // error
    //################################################
    public static void error(String message) {
    	CM_Log.message("ERROR", message);
    }
    
    public static void error(Exception e) {
    	CM_Log.error(e, "");
    }
    
    public static void error(Exception e, String message) {
    	CM_Log.message("ERROR", message);
    	System.out.println("	| Message: " + e.getMessage());
    	System.out.println("	| Exception: " + e.getCause());
    }
}
