/**
 * 
 */
package SE2.Swimv2.Session;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

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
	public long createAdmin(String username, String password) throws AdminException {
		Admin admin=new Admin(username, password);
		try {
			database.persist(admin);
			return admin.getId();
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
	public void modificaPassword(long adminId, String nuovaPassword)throws AdminException {

		if(nuovaPassword.equals("")){
			throw new AdminException("La Password non può essere vuota");
		}

		Admin admin = database.find(Admin.class, adminId);
		try {
			admin.setPassword(nuovaPassword);
			database.flush();
			return;
		} catch (NullPointerException e) {
		} catch (PersistenceException e) {
		} catch (IllegalStateException e) {
		}
		throw new AdminException("Errore, dati non modificati");
		
		
		}

	/**
	 * Dato l'id dell'mministratore, restituisce l' admin
	 */
	@Override
	public Admin getAdmin(long adminId) {
		Admin adminTrovato = database.find(Admin.class, adminId);
		return adminTrovato;
	}
	
}
