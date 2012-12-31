package SE2.Swimv2.Session;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import SE2.Swimv2.Entity.RichiestaAmicizia;
import SE2.Swimv2.Entity.User;
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
	
	
    /**
     * Default constructor. 
     */
    public GestoreRichiesteAmicizia() {
        // TODO Auto-generated constructor stub
    }

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
			}
			catch(PersistenceException e){
				throw new RichiestaAmiciziaException("Errore di persistenza");
			}
			
		}else{
			throw new RichiestaAmiciziaException("Gli User sono già amici");
		}	
	}

	@Override
	public List<RichiestaAmicizia> elencoRichiesteAmicizia(long user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean presentiNuoveRichieste(long user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void accettaRichiestaAmicizia(RichiestaAmicizia richiestaAmicizia) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rifiutaRichiestaAmicizia(RichiestaAmicizia richiestaAmicizia) {
		// TODO Auto-generated method stub
		
	}
	
	private boolean esisteRichiestaAmicizia(long fromUser, long toUser){
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
		}
		catch (EntityNotFoundException e){
		}
		catch (NoResultException e) {
		}
		return true;
	}
}
