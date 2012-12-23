package SE2.Swimv2.Session;

import java.util.Set;

import javax.ejb.Remote;

import SE2.Swimv2.Entity.Messaggio;
import SE2.Swimv2.Entity.User;
/**
 * @author Daniel Cantoni
 * Interfaccia per la gestione dei messaggi
 */
@Remote
public interface GestoreMessaggiRemote {
	
	public void inviaMessaggio(User mittente, User destinatario, String testo);
	public Set<Messaggio> elencoMessaggi(User user);
	public Boolean verificaNuoviMessaggi(User user);
	public void settaMessaggioLetto(Messaggio messaggio);
	public Messaggio getById(long id);
}
