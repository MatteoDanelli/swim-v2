/**
 * 
 */
package SE2.Swimv2.Session;

import javax.ejb.Remote;

import SE2.Swimv2.Entity.Admin;
import SE2.Swimv2.Exceptions.AdminException;

/**
 * @author Matteo Danelli
 * Interfaccia remota per la gestione delle operazioni dell'admin.
 */
@Remote
public interface GestoreAdminRemote {
	void createAdmin(String email, String password) throws AdminException;
	void modificaPassword(String email, String nuovaPassword);
	Admin getAdmin();
}
