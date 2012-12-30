package SE2.Swimv2.Session;

import java.util.List;
import javax.ejb.Remote;

import SE2.Swimv2.Entity.Messaggio;
import SE2.Swimv2.Exceptions.MessaggiException;

/**
 * @author Daniel Cantoni
 * Interfaccia per la gestione dei messaggi
 */
@Remote
public interface GestoreMessaggiRemote {
	
	public void inviaMessaggio(long mittente, long destinatario, String testo) throws MessaggiException;
	public List<Messaggio> elencoMessaggi(long user) throws MessaggiException;
	public Boolean verificaNuoviMessaggi(long user);
	public void settaMessaggioLetto(long messaggio);
	public Messaggio getById(long id);
}
