package exception;

public class FailedDBConnection extends Exception {
	private static final long serialVersionUID = 1L;
	
    @Override
    public String getMessage() {
        return "We could not connect the server to the database.";
    }
}