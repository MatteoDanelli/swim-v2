package SE2.Swimv2.Session;

import javax.ejb.Stateless;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import SE2.Swimv2.Entity.Admin;
import SE2.Swimv2.Entity.User;
import SE2.Swimv2.Exceptions.LoginException;

/**
 * Session Bean implementation class GestoreLogin
 */
@Stateless
public class GestoreLogin implements GestoreLoginRemote {
	
	@PersistenceContext(unitName = "Swimv2")
	EntityManager database;
	
	/**
	 * @param email dell'user che vuole fare il login
	 * @param password dell'user che vuole fare il login
	 * @return l'id dell'user trovato, se presente.
	 * @exception EntityNotFoundException se non esiste l'entità User
	 * @exception NoResultException se la query non restituisce risultati
	 * @exception NonUniqueResultException se il risultato non è unico
	 * @throws LoginException
	 */
	@Override
	public long loginUser(String email, String password) throws LoginException {
		try {
			Query q = database.createQuery("FROM User u WHERE u.email=:email AND u.password=:password");
			q.setParameter("email", email);
			q.setParameter("password", password);
			User result = (User) q.getSingleResult();
			return result.getId();
	
		} catch (EntityNotFoundException e) {
			e.printStackTrace();
		}
		catch (NoResultException e) {
			e.printStackTrace();
		}
		catch (NonUniqueResultException e) {
			e.printStackTrace();
		}
		throw new LoginException("L'utente non esiste!");
	}

	/**
	 * @param email dell'admin che vuole fare il login
	 * @param password dell'admin che vuole fare il login
	 * @return l'id dell'admin trovato, se presente
	 * @throws LoginException 
	 * @exception EntityNotFoundException se non esiste l'entità Admin
	 * @exception NoResultException se la query non restituisce risultati
	 * @exception NonUniqueResultException se il risultato non è unico
	 */
	@Override
	public long loginAdmin(String email, String password) throws LoginException {
		try {
			Query q = database.createQuery("FROM Admin a WHERE a.email=:email AND a.password=:password");
			q.setParameter("email", email);
			q.setParameter("password", password);
			Admin result = (Admin) q.getSingleResult();
			return result.getId();
		} catch (EntityNotFoundException e) {
		} catch (NoResultException e) {
		} catch (NonUniqueResultException e) {
		}
		throw new LoginException("L'admin non esiste!");
	}
}


