package utils;

import java.util.ArrayList;
import java.util.List;

public class ErrorsHandler {
    private boolean isValid;
    private String message;
    private List<String> debugs;
    
    public ErrorsHandler() {
        this.isValid = true;
        this.message = "";
        this.debugs = new ArrayList<String>();
    }

    public void setIsValid(boolean isValid) {
        this.isValid = isValid;
    }

    public void setError(String clientInfo, String serverInfo) {
        this.isValid = false;
        this.message = clientInfo;

        Log.error(clientInfo, serverInfo, this.debugs);
    }
    
    public void addDebug(String message) {
    	debugs.add(message);
    }

    public void setInfo(String clientInfo, String serverInfo) {
        this.isValid = true;
        this.message = clientInfo;

        Log.info(clientInfo, serverInfo, this.debugs);
    }

    public static String getMessageError(Exception e) {
        return "\n" +   "       ---------------------------------------------------------------------------------------------------" + "\n" +
                        "       | Exception           = " + e.getClass() + "\n" +
                        "       | Message             = " + e.getMessage() + "\n" +
                        "       | LocalizedMessage    = " + e.getLocalizedMessage() + "\n" +
                        "       | Cause               = " + e.getCause() + "\n" +
                        "       ---------------------------------------------------------------------------------------------------" + "\n";
    }

    public boolean isValid() {
        return isValid;
    }

    public String getMessage() {
        return message;
    }
}
