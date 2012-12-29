package SE2.Swimv2.Session;

import java.util.Set;

import javax.ejb.Remote;
import SE2.Swimv2.Entity.User;
import SE2.Swimv2.Exceptions.AmiciException;

/**
 * @author Daniel Cantoni
 * Interfaccia per la gestione degli amici
 */
@Remote
public interface GestoreAmiciRemote {
	public void aggiungiAmicizia(long idUser1,long idUser2) throws AmiciException;
	public Set<User> elencoAmici(long idUser);
	public Boolean verificaAmicizia(long idUser1,long idUser2);
}
