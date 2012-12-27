package SE2.Swimv2.Session;

import javax.ejb.Remote;

import SE2.Swimv2.Entity.LoginStatus;
/**
 * @author Daniel Cantoni
 * Interfaccia per la gestione dei login, per gli user o per l'admin.
 */
@Remote
public interface GestoreLoginRemote {

	public LoginStatus loginUser(String email, String password);
	public LoginStatus loginAdmin(String email, String password);
}
