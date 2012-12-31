package SE2.Swimv2.Session;

import java.util.Date;
import java.util.Set;

import javax.ejb.Stateless;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import SE2.Swimv2.Entity.Skill;
import SE2.Swimv2.Entity.User;

/**
 * @author Daniel Cantoni, Matteo Danelli
 * Session Bean che implementa l'interfaccia GestoreUserRemote.
 */
@Stateless
public class GestoreUser implements GestoreUserRemote {
	@PersistenceContext(unitName = "Swimv2")
	EntityManager database;
	
    /**
     *  Inserisce un nuovo utente,  assegnandoli tutti i suoi attributi.
     */
	@Override
	public long addUser(String email, String password, String nome,String cognome, String provincia, char sesso, Date datanascita,
			Set<Skill> personalSkill) {
			User newUser = new User(email, password, nome, cognome, provincia, sesso, datanascita);
    	try{
    		newUser.setSkillPossedute(personalSkill);
    		database.persist(newUser);
    	}catch(PersistenceException e){
    		//Entità già presente.
    		return -1;
    	}
		return newUser.getId();
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

	@Override
	public User getById(long id) {
		User u = database.find(User.class, id);
		return u;
	}

}
