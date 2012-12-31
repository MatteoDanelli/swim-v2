package SE2.Swimv2.Session;

import java.util.List;
import javax.ejb.Remote;
import SE2.Swimv2.Entity.RichiestaAmicizia;
import SE2.Swimv2.Exceptions.RichiestaAmiciziaException;

/**
 * @author Matteo Danelli
 * Interfaccia per la gestione delle richieste di amicizia
 */
@Remote
public interface GestoreRichiesteAmiciziaRemote {
	 void inviaRichiestaAmicizia(long fromUser, long toUser) throws RichiestaAmiciziaException;
	 List<RichiestaAmicizia> elencoRichiesteAmicizia(long user);
	 boolean presentiNuoveRichieste(long user);
	 void accettaRichiestaAmicizia(RichiestaAmicizia richiestaAmicizia);
	 void rifiutaRichiestaAmicizia(RichiestaAmicizia richiestaAmicizia);
}
