package SE2.Swimv2.Session;

import java.util.Set;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import SE2.Swimv2.Entity.User;

/**
 * Session Bean implementation class GestoreAmici
 */
@Stateless
public class GestoreAmici implements GestoreAmiciRemote {
	@PersistenceContext(unitName = "Swimv2")
	EntityManager database;
    /**
     * Default constructor. 
     */
    public GestoreAmici() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void aggiungiAmicizia(User user1, User user2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Set<User> elencoAmici(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean verificaAmicizia(User user1, User user2) {
		// TODO Auto-generated method stub
		return null;
	}

}
