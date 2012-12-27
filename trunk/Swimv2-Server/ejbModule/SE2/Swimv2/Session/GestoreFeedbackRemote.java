/**
 * 
 */
package SE2.Swimv2.Session;

import java.util.Set;

import javax.ejb.Remote;

import SE2.Swimv2.Entity.Feedback;
import SE2.Swimv2.Entity.User;

/**
 * @author Matteo Danelli
 * Interfaccia per la gestione dei feedback.
 */
@Remote
public interface GestoreFeedbackRemote {
	void creaFeedback(long idMittente, long idDestinatario, int stelleDaAssegnare, String commento);
	Set<Feedback> elencoFeedback(long userId);
	int mediaVotiFeedback(long userId);
	Feedback getById(long userId);
}
