package SE2.Swimv2.Exceptions;

public class UserException extends Exception {

	private static final long serialVersionUID = 1L;

	public UserException(){
	}

	public UserException(String s){
		super(s);
	}
}
