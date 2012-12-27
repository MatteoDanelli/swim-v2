package SE2.Swimv2.Session;

import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import SE2.Swimv2.Entity.LoginStatus;

/**
 * Session Bean implementation class GestoreLogin
 */
@Stateless
public class GestoreLogin implements GestoreLoginRemote {
	@PersistenceContext(unitName = "Swimv2")
	EntityManager database;
	
	@Override
	public LoginStatus loginUser(String email, String password) {
		Query q = database.createQuery("FROM User u WHERE u.email=:email AND u.password=:password");
		q.setParameter("email", email);
		q.setParameter("password", password);
		if (q.getSingleResult()!=null) 
			return LoginStatus.Login_OK;	
		return LoginStatus.Login_KO;
	}

	@Override
	public LoginStatus loginAdmin(String email, String password) {
		Query q = database.createQuery("FROM Admin a WHERE a.email=:email AND a.password=:password");
		q.setParameter("email", email);
		q.setParameter("password", password);
		if (q.getSingleResult()!=null) 
			return LoginStatus.Login_OK;	
		return LoginStatus.Login_KO;	
	}		
}


