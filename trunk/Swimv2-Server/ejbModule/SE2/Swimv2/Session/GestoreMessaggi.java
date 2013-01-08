package SE2.Swimv2.Session;

import java.util.GregorianCalendar;
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
 * @author Matteo Danelli
 * Session Bean implementation class GestoreMessaggi
 */
@Stateless
public class GestoreMessaggi implements GestoreMessaggiRemote {
	@PersistenceContext(unitName = "Swimv2")
	EntityManager database;
	

	@Override
	public void inviaMessaggio(long mittente, long destinatario, String testo) throws MessaggiException {
		if (mittente != destinatario) {
			Messaggio nuovoMessaggio = new Messaggio();
			User userMittente = database.find(User.class, mittente);
			User UserDestinatario = database.find(User.class, destinatario);
			nuovoMessaggio.setMittente(userMittente);
			nuovoMessaggio.setDestinatario(UserDestinatario);
			nuovoMessaggio.setTesto(testo);
			nuovoMessaggio.setDataInvio(new GregorianCalendar());
			database.persist(nuovoMessaggio);
		}
		else throw new MessaggiException("Mittente e destinario coincidenti!");
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Messaggio> elencoMessaggi(long user) throws MessaggiException {
		User userCercato = database.find(User.class, user);
		Query q = database.createQuery("FROM Messaggio m WHERE m.destinatario=:userDestinatario ORDER BY m.dataInvio desc, m.isMessaggioLetto desc");
		q.setParameter("userDestinatario", userCercato);
		try {
			List<Messaggio> elenco = (List<Messaggio>) q.getResultList();
			return elenco;
		}
		catch (EntityNotFoundException e){
		}
		catch (NoResultException e) {
		}
		catch (NonUniqueResultException e) {
		}
		throw new MessaggiException("Non esistono messaggi!");
	}

	@SuppressWarnings("unchecked")
	@Override
	public Boolean verificaNuoviMessaggi(long user) {
		User userCercato = database.find(User.class, user);
		Query q = database.createQuery("FROM Messaggio m WHERE m.destinatario=:userDestinatario AND m.isMessaggioLetto='false'");
		q.setParameter("userDestinatario", userCercato);
		List<Messaggio> elenco = (List<Messaggio>) q.getResultList();
		if (elenco.size()!=0)
			return true;
		return false;
	}

	@Override
	public void settaMessaggioLetto(long messaggio) {
		Messaggio messaggioLetto = database.find(Messaggio.class, messaggio);
		messaggioLetto.setMessaggioLetto(true);
	}

	@Override
	public Messaggio getById(long id) {
		Messaggio messaggioCercato = database.find(Messaggio.class, id);
		return messaggioCercato;
	}
}
