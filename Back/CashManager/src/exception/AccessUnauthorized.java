package exception;

import utils.servlet.HttpStatus;

public class AccessUnauthorized  extends Exception implements HttpStatusException {
	private static final long serialVersionUID = 1L;
	
	@Override
    public String getMessage() {
        return "You need to be authentificated and add the token to your header";
    }

	@Override
	public int getHttpStatus() {
		return HttpStatus.UNAUTHORIZED;
	}
}
