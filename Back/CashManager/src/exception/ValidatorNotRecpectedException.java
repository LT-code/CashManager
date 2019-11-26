package exception;

import utils.servlet.HttpStatus;

public class ValidatorNotRecpectedException extends Exception implements HttpStatusException {
	private static final long serialVersionUID = 1L;
	
	private String message;

    public ValidatorNotRecpectedException() {
        message = "Le validateur n'a pas été recpecté";
    }

    public ValidatorNotRecpectedException(String message) {
    	
        this.message = "Le validateur n'a pas été recpecté : " + message;
    }

    @Override
    public String getMessage() {
        return "Exception ValidatorNotRecpectedException : " + this.message;
    }

	@Override
	public int getHttpStatus() {
		return HttpStatus.BAD_REQUEST;
	}
}
