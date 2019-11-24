package exception;

import utils.servlet.HttpStatus;

public class AccessForbidden  extends Exception implements HttpStatusException {
	private static final long serialVersionUID = 1L;
	
	@Override
    public String getMessage() {
        return "You don't have the access with your authentification";
    }

	@Override
	public int getHttpStatus() {
		return HttpStatus.FORBIDDEN;
	}
}
