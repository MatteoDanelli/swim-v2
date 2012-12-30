/**
 * 
 */
package SE2.Swimv2.Exceptions;

/**
 * @author Matteo Danelli
 *
 */
public class LoginException extends Exception {

	private static final long serialVersionUID = 1L;

	public LoginException(){
		
	}
	
	public LoginException(String s) {
		super(s);
	}
}
