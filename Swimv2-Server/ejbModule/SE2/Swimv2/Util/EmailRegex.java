package SE2.Swimv2.Util;

import java.util.regex.Pattern;

public class EmailRegex {
	public static final String EMAIL = "^[\\w\\-]([\\.\\w])+[\\w]+@([\\w\\-]+\\.)+[a-zA-Z]{2,4}$";
	
	public boolean isValidEmail(String mail){
		if (Pattern.matches(EMAIL, mail)) {
			return true;
		}
		return false;
	}
}
