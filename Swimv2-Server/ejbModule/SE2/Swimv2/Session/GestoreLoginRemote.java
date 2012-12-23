package SE2.Swimv2.Session;

import javax.ejb.Remote;
/**
 * @author Daniel Cantoni
 * Interfaccia per la gestione del login
 */
@Remote
public interface GestoreLoginRemote {

	public void loginUser(String email, String password);
	public void loginAdmin(String email, String password);
}
