/**
 * 
 */
package SE2.Swimv2.Session;

import java.util.Set;

import javax.ejb.Remote;

import SE2.Swimv2.Entity.User;
import SE2.Swimv2.Entity.Feedback;

/**
 * @author Matteo Danelli
 * Interfaccia per la gestione dei feedback.
 */
@Remote
public interface GestoreFeedbackRemote {
	void creaFeedback(User daUser, User aUser, int stelleDaAssegnare, String commento);
	Set<Feedback> elencaFeedback();
	int mediaVotiFeedback(User userDaValutare);
}
