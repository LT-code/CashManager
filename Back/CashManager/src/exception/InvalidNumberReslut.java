package exception;

public class InvalidNumberReslut extends Exception {
	private static final long serialVersionUID = 1L;
	private int resultNumber;
	
	public InvalidNumberReslut(int resultNumber) {
		this.resultNumber = resultNumber;
	}
	
    @Override
    public String getMessage() {
        return "One result was excepted, " + resultNumber + " has been receved.";
    }
}