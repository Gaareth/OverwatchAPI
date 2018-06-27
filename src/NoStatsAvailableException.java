
public class NoStatsAvailableException extends RuntimeException{
	
	public NoStatsAvailableException() {	
		super("unchecked NoStatsAvailableException");
	}
	
	public NoStatsAvailableException(String errormsg) {
		super(errormsg);
	}

}
