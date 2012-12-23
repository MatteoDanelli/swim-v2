package SE2.Swimv2.Session;

import java.util.Set;

import javax.ejb.Remote;

import SE2.Swimv2.Entity.User;
/**
 * @author Daniel Cantoni
 * Interfaccia per la gestione degli amici
 */
@Remote
public interface GestoreAmiciRemote {
	public void aggiungiAmicizia(User user1,User user2);
	public Set<User> elencoAmici(User user);
	public Boolean verificaAmicizia(User user1,User user2);
}
