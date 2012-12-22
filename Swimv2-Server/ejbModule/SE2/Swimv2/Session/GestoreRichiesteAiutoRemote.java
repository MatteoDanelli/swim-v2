package SE2.Swimv2.Session;

import java.util.Set;

import javax.ejb.Remote;

import SE2.Swimv2.Entity.Messaggio;
import SE2.Swimv2.Entity.User;

@Remote
public interface GestoreRichiesteAiutoRemote {

	public void inviaRichiestaAiuto(User mittente, User destinatario, String testo);
	public Set<Messaggio> RichiesteAiuto(User user);
	public Boolean verificaNuoveRichiesteAiuto(User user);
	public void settaRichiestaLetta(Messaggio messaggio);
	public Messaggio getById(long id);
}
