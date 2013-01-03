package SE2.Swimv2.Session;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import SE2.Swimv2.Entity.Feedback;
import SE2.Swimv2.Entity.User;
import SE2.Swimv2.Exceptions.FeedbackException;

/**
 * Session Bean implementation class GestoreFeedback
 */
@Stateless
public class GestoreFeedback implements GestoreFeedbackRemote{
	@PersistenceContext(unitName = "Swimv2")
	EntityManager database;
	
	@EJB 
	private GestoreAmiciLocal gestoreAmici;
	

    /**
     * Questo metodo crea un nuovo Feedback
     * @throws FeedbackException 
     */
	@Override
	public void creaFeedback(long idMittente, long idDestinatario, int stelleDaAssegnare,
			String commento) throws FeedbackException {
	
		if(stelleDaAssegnare<0 || stelleDaAssegnare>5){
			throw new FeedbackException("Il numero di stelle deve essere compreso tra 0 e 5");
		}
		
		if(gestoreAmici.verificaAmicizia(idMittente, idDestinatario)){
			
			User mittente = database.find(User.class, idMittente);
			User destinatario = database.find(User.class, idDestinatario);
			
			Feedback feedback = new Feedback(mittente,destinatario,stelleDaAssegnare, commento);
			database.persist(feedback);
		}else{
			throw new FeedbackException("Impossibile creare il Feedback, gli User non sono amici");
		}

	}

    /**
     * Questo metodo restituisce la lista di feedback, associata ad un utente
     */
	@SuppressWarnings("unchecked")
	@Override
	public List<Feedback> elencoFeedback(long userId) {

		User user = database.find(User.class, userId);
		Query q = database.createQuery("FROM Feedback f WHERE f.destinatario=:user ORDER BY f.dataPubblicazione desc , f.id desc");
		q.setParameter("user", user);
		
		List<Feedback> result =(List<Feedback>) q.getResultList();
		return result;

	}

    /**
     * Questo metodo restituisce la media dei voti relativa ai feedback di un utente
     */
	@Override
	public double mediaVotiFeedback(long userId) {
		User user = database.find(User.class, userId);
		Query q = database.createQuery("SELECT avg(f.StelleAssegnate) FROM Feedback f WHERE f.destinatario=:user ");
		q.setParameter("user", user);
		
		Double result=(Double) q.getSingleResult();
		
		return result.doubleValue();
	}

	/**
	 * Questo metodo restituisce un feedback dato il suo id
	 */
	@Override
	public Feedback getById(long id) {
		Feedback feedback= database.find(Feedback.class, id);
		return feedback;
	}

}
