package SE2.Swimv2.Session;

import java.util.HashSet;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import SE2.Swimv2.Entity.User;
import SE2.Swimv2.Exceptions.AmiciException;

/**
 * Session Bean implementation class GestoreAmici
 */

@Stateless
public class GestoreAmici implements GestoreAmiciRemote, GestoreAmiciLocal {
	@PersistenceContext(unitName = "Swimv2")
	EntityManager database;
	
	/**
     * Costruttore
     */
    public GestoreAmici() {
        // TODO Auto-generated constructor stub
    }
    
    /**
     * Questo metodo aggiunge una relazione di amicizia tra i 2 user
     * 
     */
	@Override
	public void aggiungiAmicizia(long idUser1, long idUser2) throws AmiciException{

		if(this.verificaAmicizia(idUser1, idUser2)){
			throw new AmiciException("Gli User sono già amici");
		}
		
		if(idUser1!=idUser2){
			User user1 = database.find(User.class, idUser1);
			User user2 = database.find(User.class, idUser2);
		
			user1.addAmico(user2);
			database.flush();
		}else{
			throw new AmiciException("Un utente non può essere amico di se stesso");
		}
	}
	
	/**
	 * Questo metodo restituisce l' insieme degli amici di un utente
	 */
	@Override
	public Set<User> elencoAmici(long idUser) {
		User user = database.find(User.class, idUser);
		Set<User> returnSet = new HashSet<User>();
		
		for(User u: user.getAmici1()){
			returnSet.add(u);
		}
		for(User u: user.getAmici2()){
			returnSet.add(u);
		}
		return returnSet;
	}
	
	/**
	 * Questo metodo verifica se 2 user sono amici
	 */
	@Override
	public Boolean verificaAmicizia(long idUser1, long idUser2) {
		User user2 = database.find(User.class, idUser2);
		Set<User> amiciUser1;

		amiciUser1= elencoAmici(idUser1);
		if(amiciUser1.contains(user2)){
			return true;
		}
		return false;
	}

}
