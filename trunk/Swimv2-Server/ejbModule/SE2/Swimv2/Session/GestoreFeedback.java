package SE2.Swimv2.Session;

import java.util.HashSet;
import java.util.Set;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import SE2.Swimv2.Entity.Feedback;
import SE2.Swimv2.Entity.User;

/**
 * Session Bean implementation class GestoreFeedback
 */
@Stateless
public class GestoreFeedback implements GestoreFeedbackRemote{
	@PersistenceContext(unitName = "Swimv2")
	EntityManager database;
    /**
     * Default constructor. 
     */
    public GestoreFeedback() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void creaFeedback(long idMittente, long idDestinatario, int stelleDaAssegnare,
			String commento) {
		User mittente = database.find(User.class, idMittente);
		User destinatario = database.find(User.class, idDestinatario);
				
		Feedback feedback = new Feedback(mittente,destinatario,stelleDaAssegnare, commento);
		database.persist(feedback);
	}

	@Override
	public Set<Feedback> elencoFeedback(long userId) {

		User user = database.find(User.class, userId);
		Set<Feedback> set = new HashSet<Feedback>();
		for(Feedback feed: user.getFeedbackRicevuti()){
			set.add(new Feedback(feed.getMittente(), feed.getDestinatario(), feed.getStelleAssegnate(), feed.getCommento()));
		}
		return set;
	}

	@Override
	public int mediaVotiFeedback(long userId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Feedback getById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
