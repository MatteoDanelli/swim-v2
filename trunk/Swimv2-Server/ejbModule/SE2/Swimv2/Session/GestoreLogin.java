package SE2.Swimv2.Session;

import javax.ejb.Stateless;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import SE2.Swimv2.Entity.Admin;
import SE2.Swimv2.Entity.User;

/**
 * Session Bean implementation class GestoreLogin
 */
@Stateless
public class GestoreLogin implements GestoreLoginRemote {
	
	@PersistenceContext(unitName = "Swimv2")
	EntityManager database;
	
	private static long NOT_FOUND = -1;
	
	/**
	 * @param email dell'user che vuole fare il login
	 * @param password dell'user che vuole fare il login
	 * @return l'id dell'user trovato, se presente; -1 altrimenti.
	 */
	@Override
	public long loginUser(String email, String password) {
		try{
		Query q = database.createQuery("FROM User u WHERE u.email=:email AND u.password=:password");
		q.setParameter("email", email);
		q.setParameter("password", password);
		User result = (User) q.getSingleResult();
		if (result!=null) 
			return result.getId();	
		
		}catch (EntityNotFoundException exc) { 
    	} catch (javax.persistence.NoResultException exc) { 
    	} catch (NonUniqueResultException exc) {
    	}
    	return NOT_FOUND;
	}

	/**
	 * @param email dell'user che vuole fare il login
	 * @param password dell'user che vuole fare il login
	 * @return l'id dell'admin trovato, se presente; -1 altrimenti.
	 */
	@Override
	public long loginAdmin(String email, String password) {
		Query q = database.createQuery("FROM Admin a WHERE a.email=:email AND a.password=:password");
		q.setParameter("email", email);
		q.setParameter("password", password);
		Admin result = (Admin) q.getSingleResult();
		if (result!=null) 
			return result.getId();	
		return NOT_FOUND;	
	}		
}


