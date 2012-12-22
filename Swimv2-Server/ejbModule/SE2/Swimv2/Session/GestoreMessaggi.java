package SE2.Swimv2.Session;

import java.util.Set;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import SE2.Swimv2.Entity.Messaggio;
import SE2.Swimv2.Entity.User;

/**
 * Session Bean implementation class GestoreMessaggi
 */
@Stateless
public class GestoreMessaggi implements GestoreMessaggiRemote {
	@PersistenceContext(unitName = "Swimv2")
	EntityManager database;
	
    /**
     * Default constructor. 
     */
    public GestoreMessaggi() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void inviaMessaggio(User mittente, User destinatario, String testo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Set<Messaggio> elencoMessaggi(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean verificaNuoviMessaggi(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void settaMessaggioLetto(Messaggio messaggio) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Messaggio getById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
