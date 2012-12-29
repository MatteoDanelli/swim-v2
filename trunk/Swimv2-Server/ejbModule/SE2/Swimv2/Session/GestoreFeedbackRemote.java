/**
 * 
 */
package SE2.Swimv2.Session;

import java.util.List;
import javax.ejb.Remote;
import SE2.Swimv2.Entity.Feedback;
import SE2.Swimv2.Exceptions.FeedbackException;

/**
 * @author Matteo Danelli
 * Interfaccia per la gestione dei feedback.
 */
@Remote
public interface GestoreFeedbackRemote {
	void creaFeedback(long idMittente, long idDestinatario, int stelleDaAssegnare, String commento) throws FeedbackException;
	List<Feedback> elencoFeedback(long userId);
	double mediaVotiFeedback(long userId);
	Feedback getById(long userId);
}
