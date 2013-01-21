package SE2.Swimv2.Session;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
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
		
		User userMittente = database.find(User.class, mittente);
		User UserDestinatario = database.find(User.class, destinatario);
		
		if(testo==null || testo.equals("")){
			throw new MessaggiException("IL messaggio è vuoto");
		}
		
		if (mittente == destinatario) {
			throw new MessaggiException("Mittente e destinario coincidenti!");
		}
		
		try{
			Messaggio nuovoMessaggio= new Messaggio(userMittente, UserDestinatario, null, false, testo);
			database.persist(nuovoMessaggio);
			return;
		}catch (PersistenceException e) {				
		}catch(NullPointerException e){			
		}
		throw new MessaggiException("Errore accesso al database");
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Messaggio> elencoMessaggi(long user){
		
		User userCercato = database.find(User.class, user);
		Query q = database.createQuery("FROM Messaggio m WHERE m.destinatario=:userDestinatario AND m.isRichiestaAiuto=false ORDER BY m.dataInvio desc, m.isMessaggioLetto desc");
		q.setParameter("userDestinatario", userCercato);

		List<Messaggio> elenco = (List<Messaggio>) q.getResultList();
		return elenco;
	}

	@SuppressWarnings("unchecked")
	@Override
	public int verificaNuoviMessaggi(long user) {
		
		User userCercato = database.find(User.class, user);
		Query q = database.createQuery("FROM Messaggio m WHERE m.destinatario=:userDestinatario AND m.isMessaggioLetto=false AND m.isRichiestaAiuto=false");
		q.setParameter("userDestinatario", userCercato);
		List<Messaggio> elenco = (List<Messaggio>) q.getResultList();
		
		return elenco.size();

	}

	@Override
	public void settaMessaggioLetto(long messaggio) throws MessaggiException {
		
		Messaggio messaggioLetto = database.find(Messaggio.class, messaggio);
	
		try{	
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
	public Messaggio getSingleMessage(long dest, long idMex)throws MessaggiException{
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
