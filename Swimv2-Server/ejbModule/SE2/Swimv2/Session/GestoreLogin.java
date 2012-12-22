package SE2.Swimv2.Session;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Session Bean implementation class GestoreLogin
 */
@Stateless
public class GestoreLogin implements GestoreLoginRemote {
	@PersistenceContext(unitName = "Swimv2")
	EntityManager database;
	
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
