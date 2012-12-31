package SE2.Swimv2.Session;

import java.util.List;
import javax.ejb.Remote;
import SE2.Swimv2.Entity.RichiestaAmicizia;
import SE2.Swimv2.Exceptions.AmiciException;
import SE2.Swimv2.Exceptions.RichiestaAmiciziaException;

/**
 * @author Matteo Danelli
 * Interfaccia per la gestione delle richieste di amicizia
 */
@Remote
public interface GestoreRichiesteAmiciziaRemote {
	 void inviaRichiestaAmicizia(long fromUser, long toUser) throws RichiestaAmiciziaException;
	 List<RichiestaAmicizia> elencoRichiesteAmicizia(long userid);
	 int numeroDiNuoveRichieste(long userid);
	 void accettaRichiestaAmicizia(long idRichiestaAmicizia,long currentUser)throws AmiciException,RichiestaAmiciziaException;
	 void rifiutaRichiestaAmicizia(long idRichiestaAmicizia,long currentUser)throws RichiestaAmiciziaException;
	 public boolean esisteRichiestaAmicizia(long fromUser, long toUser);
}
