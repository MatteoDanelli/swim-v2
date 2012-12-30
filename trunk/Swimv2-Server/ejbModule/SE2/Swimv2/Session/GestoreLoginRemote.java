package SE2.Swimv2.Session;

import javax.ejb.Remote;

import SE2.Swimv2.Exceptions.LoginException;

/**
 * @author Daniel Cantoni
 * Interfaccia per la gestione dei login, per gli user o per l'admin.
 */
@Remote
public interface GestoreLoginRemote {

	public long loginUser(String email, String password) throws LoginException;
	public long loginAdmin(String email, String password) throws LoginException;
}
