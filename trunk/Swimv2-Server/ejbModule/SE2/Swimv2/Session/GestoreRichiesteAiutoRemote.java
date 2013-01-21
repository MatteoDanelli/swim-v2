package SE2.Swimv2.Session;

import java.util.List;

import javax.ejb.Remote;

import SE2.Swimv2.Entity.Messaggio;
import SE2.Swimv2.Entity.Skill;
import SE2.Swimv2.Exceptions.MessaggiException;
/**
 * @author Daniel Cantoni
 * Interfaccia per la gestione delle richieste di aiuto
 */
@Remote
public interface GestoreRichiesteAiutoRemote {

	public void inviaRichiestaAiuto(long mittente, long destinatario,Skill skill, String testo) throws MessaggiException;
	public List<Messaggio> elencoRichiesteAiuto(long user);
	public int verificaNuoveRichiesteAiuto(long user);
	public void settaRichiestaLetta(long messaggio)throws MessaggiException;
	public Messaggio getSingleRequest(long dest, long idMex)throws MessaggiException;
	public Messaggio getById(long id);
}
