/**
 * 
 */
package SE2.Swimv2.Session;

import javax.ejb.Remote;

/**
 * @author Matteo Danelli
 * Interfaccia remota per la gestione delle operazioni dell'admin.
 */
@Remote
public interface GestoreAdminRemote {
	void modificaPassword(String nuovaPassword);
}
