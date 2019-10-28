package utils;

public class ErrorsHandler {
    private boolean isValid;
    private String message;

    public ErrorsHandler() {
        this.isValid = true;
        this.message = "";
    }

    public void setIsValid(boolean isValid) {
        this.isValid = isValid;
    }

    public void setError(String clientInfo, String serverInfo) {
        this.isValid = false;
        this.message = clientInfo;

        Log.error(clientInfo, serverInfo);
    }

    public void setInfo(String clientInfo, String serverInfo) {
        this.isValid = true;
        this.message = clientInfo;

        Log.info(clientInfo, serverInfo);
    }

    public static String getMessageError(Exception e) {
        return "\n" +   "       ===================================================================================================" + "\n" +
                        "       | Exception           = " + e.getClass() + "\n" +
                        "       | Message             = " + e.getMessage() + "\n" +
                        "       | LocalizedMessage    = " + e.getLocalizedMessage() + "\n" +
                        "       | Cause               = " + e.getCause() + "\n" +
                        "       ===================================================================================================" + "\n";
    }

    public boolean isValid() {
        return isValid;
    }

    public String getMessage() {
        return message;
    }
}
