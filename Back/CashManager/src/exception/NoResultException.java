package exception;

public class NoResultException extends Exception {
	private static final long serialVersionUID = 1L;

	private String message;

    public NoResultException() {
        message = "message not defined";
    }

    public NoResultException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return "Exception NoResultException : " + this.message;
    }
}

