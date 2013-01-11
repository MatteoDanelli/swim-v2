package SE2.Swimv2.Session;

import java.util.LinkedList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

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
     * Questo metodo aggiunge una relazione di amicizia tra 2 user
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
		
			try{
				user1.addAmico(user2);
				database.flush();
				return;
			}catch(NullPointerException e){
				throw new AmiciException("Errore, utente non presente nel database");
			}catch (PersistenceException e) {
			}catch (IllegalStateException e) {
			}
			throw new AmiciException("Errore");
			
		}else{
			throw new AmiciException("Un utente non può essere amico di se stesso");
		}
	}
	
	/**
	 * Questo metodo restituisce l' insieme degli amici di un utente
	 */
	@Override
	public List<User> elencoAmici(long idUser) {
		User user = database.find(User.class, idUser);
		List<User> list = new LinkedList<User>();
		
		if(user!=null){
			for(User u: user.getAmici1()){
				list.add(u);
			}
			for(User u: user.getAmici2()){
				list.add(u);
			}
		}
		
		return list;
	}
	
	/**
	 * Questo metodo verifica se 2 user sono amici
	 */
	@Override
	public Boolean verificaAmicizia(long idUser1, long idUser2) {
		User user2 = database.find(User.class, idUser2);
		List<User> amiciUser1;

		try{
			amiciUser1= elencoAmici(idUser1);
			if(amiciUser1.contains(user2)){
				return true;
			}

		}catch(NullPointerException e){			
		}
		
		return false;
	}
}
