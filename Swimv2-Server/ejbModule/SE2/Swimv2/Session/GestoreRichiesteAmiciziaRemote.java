/**
 * 
 */
package SE2.Swimv2.Session;

import java.util.Set;

import javax.ejb.Remote;

import SE2.Swimv2.Entity.RichiestaAmicizia;
import SE2.Swimv2.Entity.User;

/**
 * @author Matteo Danelli
 * Interfaccia per la gestione delle richieste di amicizia
 */
@Remote
public interface GestoreRichiesteAmiciziaRemote {
	 void inviaRichiestaAmicizia(User fromUser, User toUser);
	 Set<RichiestaAmicizia> elencaRichiesteAmicizia(User user);
	 boolean presentiNuoveRichieste(User user);
	 void accettaRichiestaAmicizia(RichiestaAmicizia richiestaDaAccettare);
	 void rifiutaRichiestaAmicizia(RichiestaAmicizia richiestaDaRifiutare);
	 
}
