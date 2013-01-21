package SE2.Swimv2.Session;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import SE2.Swimv2.Entity.Messaggio;
import SE2.Swimv2.Entity.Skill;
import SE2.Swimv2.Entity.User;
import SE2.Swimv2.Exceptions.MessaggiException;

/**
 * Session Bean implementation class GestoreRichiesteAiuto
 */
@Stateless
public class GestoreRichiesteAiuto implements GestoreRichiesteAiutoRemote {
	@PersistenceContext(unitName = "Swimv2")
	EntityManager database;

	@Override
	public void inviaRichiestaAiuto(long mittente, long destinatario,
			Skill skill, String testo) throws MessaggiException {

		User userMittente = database.find(User.class, mittente);
		User UserDestinatario = database.find(User.class, destinatario);

		if (skill == null) {
			throw new MessaggiException("La skill è null");
		}

		if (mittente == destinatario) {
			throw new MessaggiException("Mittente e destinario coincidenti!");
		}
		try {
			Messaggio nuovoMessaggio = new Messaggio(userMittente,
					UserDestinatario, skill, true, testo);
			database.persist(nuovoMessaggio);
			return;
		} catch (PersistenceException e) {
		} catch (NullPointerException e) {
		}
		throw new MessaggiException("Errore accesso al database");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Messaggio> elencoRichiesteAiuto(long user) {

		User userCercato = database.find(User.class, user);
		Query q = database
				.createQuery("FROM Messaggio m WHERE m.destinatario=:userDestinatario AND m.isRichiestaAiuto=true ORDER BY m.dataInvio desc, m.isMessaggioLetto desc");
		q.setParameter("userDestinatario", userCercato);

		List<Messaggio> elenco = (List<Messaggio>) q.getResultList();
		return elenco;
	}

	@SuppressWarnings("unchecked")
	@Override
	public int verificaNuoveRichiesteAiuto(long user) {

		User userCercato = database.find(User.class, user);
		Query q = database
				.createQuery("FROM Messaggio m WHERE m.destinatario=:userDestinatario AND m.isMessaggioLetto=false AND m.isRichiestaAiuto=true");
		q.setParameter("userDestinatario", userCercato);
		List<Messaggio> elenco = (List<Messaggio>) q.getResultList();

		return elenco.size();

	}

	@Override
	public void settaRichiestaLetta(long messaggio) throws MessaggiException {

		Messaggio messaggioLetto = database.find(Messaggio.class, messaggio);

		try {
			messaggioLetto.setMessaggioLetto(true);
			database.flush();
			return;
		} catch (NullPointerException e) {
		} catch (PersistenceException e) {
		} catch (IllegalStateException e) {
		}
		throw new MessaggiException("Errore, dati non modificati");
	}
	
	@Override
	public Messaggio getSingleRequest(long dest, long idMex)throws MessaggiException{
		Messaggio messaggioCercato = database.find(Messaggio.class, idMex);
		try{
			if(messaggioCercato.getDestinatario().getId()==dest){
				return messaggioCercato;
			}
		}catch (NullPointerException e) {
		}
		throw new MessaggiException("Errore, accesso negato");
	}

	@Override
	public Messaggio getById(long id) {
		Messaggio messaggioCercato = database.find(Messaggio.class, id);
		return messaggioCercato;
	}

}
