package SE2.Swimv2.Session;

import java.util.HashSet;
import java.util.Set;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import SE2.Swimv2.Entity.Feedback;
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
	public void aggiungiAmicizia(long idUser1, long idUser2) {
		User user1 = database.find(User.class, idUser1);
		User user2 = database.find(User.class, idUser2);
		
		user1.addAmico(user2);
	}
	
	@Override
	public Set<User> elencoAmici(long idUser) {
		User user = database.find(User.class, idUser);
		Set<User> set = new HashSet<User>();
		for(User u: user.getAmici1()){
			set.add(u);
		}
		for(User u: user.getAmici2()){
			set.add(u);
		}
		return set;
	}
	@Override
	public Boolean verificaAmicizia(long idUser1, long idUser2) {
		// TODO Auto-generated method stub
		return null;
	}



}
