package SE2.Swimv2.Session;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import SE2.Swimv2.Entity.RichiestaAmicizia;
import SE2.Swimv2.Entity.User;
import SE2.Swimv2.Exceptions.AmiciException;
import SE2.Swimv2.Exceptions.RichiestaAmiciziaException;

/**
 * Session Bean implementation class GestoreRichiesteAmicizia
 */
@Stateless
public class GestoreRichiesteAmicizia implements GestoreRichiesteAmiciziaRemote{

	@PersistenceContext(unitName = "Swimv2")
	EntityManager database;
	@EJB 
	private GestoreAmiciLocal gestoreAmici;
	

	@Override
	public void inviaRichiestaAmicizia(long fromUser, long toUser)
			throws RichiestaAmiciziaException {
		
		if(this.esisteRichiestaAmicizia(fromUser, toUser)){
			throw new RichiestaAmiciziaException("Esiste già una richiesta di amicizia");			
		}
		
		if(!gestoreAmici.verificaAmicizia(fromUser, toUser)){
			
			User mittente = database.find(User.class, fromUser);
			User destinatario = database.find(User.class, toUser);
			
			RichiestaAmicizia richiesta = new RichiestaAmicizia(mittente, destinatario);
			
			try{
				database.persist(richiesta);
				database.flush();
			}
			catch(PersistenceException e){
				throw new RichiestaAmiciziaException("Errore di persistenza");
			}
			
		}else{
			throw new RichiestaAmiciziaException("Gli User sono già amici");
		}	
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RichiestaAmicizia> elencoRichiesteAmicizia(long userid) {
		User user = database.find(User.class, userid);
		Query q = database.createQuery("FROM RichiestaAmicizia r WHERE r.destinatario=:user ORDER BY r.id desc");
		q.setParameter("user", user);
		
		List<RichiestaAmicizia> result =(List<RichiestaAmicizia>) q.getResultList();
		return result;

	}

	@Override
	public void accettaRichiestaAmicizia(long idRichiestaAmicizia,long currentUser) throws AmiciException, RichiestaAmiciziaException{
		
		RichiestaAmicizia richiesta = database.find(RichiestaAmicizia.class, idRichiestaAmicizia);
		//verifico che la richiesta esiste
		if(richiesta!=null){
			User mittente = richiesta.getMittente();
			User destinatario = richiesta.getDestinatario();
			
			//verifico che il destinatario sia colui che ha accettato la richiesta
			if(destinatario.getId()!=currentUser){
				throw new RichiestaAmiciziaException("Accesso Negato");
			}
			
			try{
				database.remove(richiesta);
			}
			catch(PersistenceException e){
				System.err.println("Impossibile Rimovere la richiestaAmicizia :" + richiesta.getId());
			}
			
			try {
				gestoreAmici.aggiungiAmicizia(mittente.getId(), destinatario.getId());
			} catch (AmiciException e) {
				throw e;
			}
			
			return;
		}
		throw new RichiestaAmiciziaException("Non esiste la richiesta d'amicizia specificata");
		
	}

	@Override
	public void rifiutaRichiestaAmicizia(long idRichiestaAmicizia,long currentUser)throws RichiestaAmiciziaException {
		
		RichiestaAmicizia richiesta = database.find(RichiestaAmicizia.class, idRichiestaAmicizia);
		//verifico che la richiesta esiste
		if(richiesta!=null){
			User destinatario = richiesta.getDestinatario();
			
			//verifico che il destinatario sia colui che ha rifiutato la richiesta
			if(destinatario.getId()!=currentUser){
				throw new RichiestaAmiciziaException("Accesso Negato");
			}
			
			try{
				database.remove(richiesta);
			}
			catch(PersistenceException e){
				System.err.println("Impossibile Rimovere la richiestaAmicizia :" + richiesta.getId());
			}
		
			return;
		}
		throw new RichiestaAmiciziaException("Non esiste la richiesta d'amicizia specificata");
		
	}
	
	@Override
	public boolean esisteRichiestaAmicizia(long fromUser, long toUser){
		User mittente = database.find(User.class, fromUser);
		User destinatario = database.find(User.class, toUser);
		
		Query q = database.createQuery("SELECT count(r) FROM RichiestaAmicizia r WHERE (r.mittente=:mittente and r.destinatario=:destinatario)"+
									   "or (r.mittente=:destinatario and r.destinatario=:mittente)");
		q.setParameter("mittente", mittente);
		q.setParameter("destinatario", destinatario);
		
		try {
			Long num= (Long) q.getSingleResult();
			if(num.longValue()==0){
				return false;
			}
		}catch (EntityNotFoundException e){
		}catch (NoResultException e) {
		}catch (NonUniqueResultException e){
		}
		return true;
	}
}
