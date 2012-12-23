package SE2.Swimv2.Session;

import java.util.Date;
import java.util.Set;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;


import SE2.Swimv2.Entity.Skill;
import SE2.Swimv2.Entity.User;

/**
 * Session Bean che implementa l'interfaccia GestoreUserRemote.
 */
@Stateless
public class GestoreUser implements GestoreUserRemote {
	@PersistenceContext(unitName = "Swimv2")
	EntityManager database;
	
    /**
     * Inserisce un nuovo utente 
     */
	@Override
	public User addUser(String email, String password, String nome,
			String cognome, String provincia, char sesso, Date datanascita,
			Set<Skill> personalSkill) {
			
    	try{
    		database.persist(new User(email, password, nome, cognome, provincia, sesso, datanascita));
    	}catch(EntityExistsException e){
    		//Entità già presente. Non verrà inserita ma la sua id verrà saltata.
    	}
		return null;
	}

	/**
	 * 
	 */
	@Override
	public void modificaEmail(User user, String email) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modificaPassword(User user, String password) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modificaAnagrafica(String nome, String cognome,
			String provincia, char sesso, Date datanascita) {
		// TODO Auto-generated method stub
		
	}

}
