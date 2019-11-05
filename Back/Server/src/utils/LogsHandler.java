package utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LogsHandler {
    private boolean isValid;
    private String principalMessages;
    private List<String> messages;
    
    public LogsHandler() {
        this.isValid = true;
        this.messages = new ArrayList<String>();
        this.principalMessages = "";
    }
    
    public String getPrincipalMessages() {
		return principalMessages;
    }
    
    public boolean isValid() {
        return isValid;
    }
    
    public void addError(Exception e) {
    	addError(e.getMessage(), LogsHandler.getMessageError(e));
    }
    
    public void addError(String message) {
    	addError(message, message);
    }
    
    public void addError(String principalMessage, String message) {
    	this.isValid = false;
    	//this.principalMessages += (principalMessages == "" ? "" : "\n") + principalMessage;
    	this.principalMessages = principalMessage;
    	messages.add("ERROR | /!\\ " + message);
    }
    
    public void addDebug(String message) {
    	messages.add("DEBUG | --> " + message);
    }

    public void addInfo(String message) {
    	this.isValid = true && this.isValid;
    	messages.add("INFO  | --> " + message);
    }
    
    public void displayLogs() {
    	System.out.println("");
		System.out.println("================================================================================================================");
		if (messages.size() > 0)
			for (String message : messages)
				System.out.println(getHoursDate() + message);
		messages.removeAll(messages);
		principalMessages = "";
		this.isValid = true;
    }

	public static String getHoursDate() {
		return new Date().toString() + " | ";
	}
	
	public static String getMessageError(Exception e) {
        return "\n" +   "       ---------------------------------------------------------------------------------------------------" + "\n" +
                        "       | Exception           = " + e.getClass() + "\n" +
                        "       | Message             = " + e.getMessage() + "\n" +
                        "       | LocalizedMessage    = " + e.getLocalizedMessage() + "\n" +
                        "       | Cause               = " + e.getCause() + "\n" +
                        "       ---------------------------------------------------------------------------------------------------" + "\n";
    }
}
