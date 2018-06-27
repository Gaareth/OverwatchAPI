package exceptions;

public class NotAUserException extends Exception{
	
	
	public NotAUserException() {	
		super("unchecked NotAUserException");
	}
	
	public NotAUserException(String errormsg) {
		super(errormsg);
	}

}

