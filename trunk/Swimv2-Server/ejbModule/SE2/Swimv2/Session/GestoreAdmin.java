/**
 * 
 */
package SE2.Swimv2.Session;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import SE2.Swimv2.Entity.Admin;
import SE2.Swimv2.Exceptions.AdminException;

/**
 * @author Matteo Danelli
 * Implementazione dell'interfaccia GestoreAdminRemote
 */
@Stateless
public class GestoreAdmin implements GestoreAdminRemote {
	@PersistenceContext(unitName = "Swimv2")
	EntityManager database;

	//Crea l'admin con la mail e la password dati come parametri
	@Override
	public void createAdmin(String email, String password) throws AdminException {
		try {
			database.persist(new Admin(email, password));
		}
		catch (PersistenceException e)
		{
			throw new AdminException("Admin già presente!");
		}
	}

	/**
	 * Consente la modifica della password dell'admin. E' necessaria l'email.
	 */	
	@Override
	public void modificaPassword(String email, String nuovaPassword) {
		Query q = database.createQuery("UPDATE Admin a SET a.password=:password WHERE a.email=:email");
		q.setParameter("email", email);
		q.setParameter("password", nuovaPassword);
		q.executeUpdate();
		}

	@Override
	public Admin getAdmin() {
		Query q = database.createQuery("FROM Admin");
		Admin adminTrovato = (Admin) q.getSingleResult();
		return adminTrovato;
	}
	
}