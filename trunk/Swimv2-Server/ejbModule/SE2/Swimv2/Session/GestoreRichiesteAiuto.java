package SE2.Swimv2.Session;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import SE2.Swimv2.Entity.Messaggio;
import SE2.Swimv2.Entity.User;
import SE2.Swimv2.Exceptions.MessaggiException;

/**
 * Session Bean implementation class GestoreRichiesteAiuto
 */
@Stateless
public class GestoreRichiesteAiuto implements GestoreRichiesteAiutoRemote {
	@PersistenceContext(unitName = "Swimv2")
	EntityManager database;
	
    /**
     * Default constructor. 
     */
    public GestoreRichiesteAiuto() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void inviaRichiestaAiuto(long mittente, long destinatario, String testo) throws MessaggiException {
		if (mittente != destinatario) {
			Messaggio nuovoMessaggio = new Messaggio();
			User userMittente = database.find(User.class, mittente);
			User UserDestinatario = database.find(User.class, destinatario);
			nuovoMessaggio.setMittente(userMittente);
			nuovoMessaggio.setDestinatario(UserDestinatario);
			nuovoMessaggio.setTesto(testo);
			nuovoMessaggio.setDataInvio(new Date());
			nuovoMessaggio.setRichiestaAiuto(true);
			database.persist(nuovoMessaggio);
		}
		else throw new MessaggiException("Mittente e destinario coincidenti!");
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Messaggio> elencoRichiesteAiuto(long user) throws MessaggiException {
		User userCercato = database.find(User.class, user);
		Query q = database.createQuery("FROM Messaggio m WHERE m.destinatario=:userDestinatario ORDER BY m.dataInvio desc, m.isMessaggioLetto desc");
		q.setParameter("userDestinatario", userCercato);
		try {
			List<Messaggio> elenco = (List<Messaggio>) q.getResultList();
			return elenco;
		} catch (EntityNotFoundException e) {
		} catch (NoResultException e) {
		} catch (NonUniqueResultException e) {
		}
		throw new MessaggiException("Non esistono messaggi!");
	}

	@SuppressWarnings("unchecked")
	@Override
	public Boolean verificaNuoveRichiesteAiuto(long user) {
		User userCercato = database.find(User.class, user);
		Query q = database.createQuery("FROM Messaggio m WHERE m.destinatario=:userDestinatario AND m.isMessaggioLetto='false'");
		q.setParameter("userDestinatario", userCercato);
		List<Messaggio> elenco = (List<Messaggio>) q.getResultList();
		if (elenco.size()!=0)
			return true;
		return false;
	}

	@Override
	public void settaRichiestaLetta(long messaggio) {
		Messaggio messaggioLetto = database.find(Messaggio.class, messaggio);
		messaggioLetto.setMessaggioLetto(true);		
	}

	@Override
	public Messaggio getById(long id) {
		Messaggio messaggioCercato = database.find(Messaggio.class, id);
		return messaggioCercato;
	}

}
