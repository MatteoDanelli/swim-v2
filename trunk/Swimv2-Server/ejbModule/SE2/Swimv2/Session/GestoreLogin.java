package SE2.Swimv2.Session;

import javax.ejb.Stateless;

/**
 * Session Bean implementation class GestoreLogin
 */
@Stateless
public class GestoreLogin implements GestoreLoginRemote {

    /**
     * Default constructor. 
     */
    public GestoreLogin() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void loginUser(String email, String password) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void loginAdmin(String email, String password) {
		// TODO Auto-generated method stub
		
	}

}
