package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import utils.servlet.HttpStatus;

public class LogsHandler {
    private boolean isValid;
    private String principalMessages;
    private List<String> messages;
    private int httpStatus;
    
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
    
    public int getHttpStatus() {
    	return httpStatus;
    }
    
    public void setHttpStatus(int status) {
    	this.httpStatus = status;
    }
    
    public void addError(Exception e, int status) {
    	addError(e.getMessage(), LogsHandler.getMessageError(e), status);
    }
    
    public void addError(String message, int status) {
    	addError(message, message, status);
    }
    
    public void addError(String principalMessage, String fullMessage, int status) {
    	this.isValid = false;
    	//this.principalMessages += (principalMessages == "" ? "" : "\n") + principalMessage;
    	this.principalMessages = principalMessage;
    	this.httpStatus = status;
    	messages.add("ERROR | /!\\ Status : " + status + " " + fullMessage);
    }
    
    public void addDebug(String message) {
    	messages.add("DEBUG | --> " + message);
    }

    public void addInfo(String message) {
    	addInfo(message, HttpStatus.SUCCESS);
    }
    
    public void addInfo(String message, int status) {
    	this.isValid = true && this.isValid;
    	this.httpStatus = status;
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
    
    public String getLastInfo() {
    	for(int i = messages.size()-1; i >= 0; i--)
    		if(messages.get(i).substring(0, 4).equals("INFO"))
    			return messages.get(i).substring(12);
    	return "";
    }

	public static String getHoursDate() {
		return new Date().toString() + " | ";
	}
	
	public static String getMessageError(Exception e) {
        return "\n" +   "       ---------------------------------------------------------------------------------------------------" + "\n" +
                        "       | Exception           = " + e.getClass() + "\n" +
                        "       | Message             = " + e.getMessage() + "\n" +
                        "       | LocalizedMessage    = " + Arrays.toString(e.getStackTrace()) + "\n" +
                        "       ---------------------------------------------------------------------------------------------------" + "\n";
	}
}
