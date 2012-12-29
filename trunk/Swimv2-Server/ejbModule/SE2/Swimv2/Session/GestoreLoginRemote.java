package SE2.Swimv2.Session;

import javax.ejb.Remote;

/**
 * @author Daniel Cantoni
 * Interfaccia per la gestione dei login, per gli user o per l'admin.
 */
@Remote
public interface GestoreLoginRemote {

	public long loginUser(String email, String password);
	public long loginAdmin(String email, String password);
}
