package SE2.Swimv2.Session;

import java.util.Date;
import java.util.Set;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;


import SE2.Swimv2.Entity.Skill;
import SE2.Swimv2.Entity.User;

/**
 * Session Bean implementation class GestoreUser
 */
@Stateless
public class GestoreUser implements GestoreUserRemote {
	@PersistenceContext(unitName = "Swimv2")
	EntityManager database;
	
    /**
     * Costruttore di Default 
     */
    public GestoreUser() {
        // TODO Auto-generated constructor stub
    }

    /**
     * Inserisce un nuovo utente 
     */
	@Override
	public User addUser(String email, String password, String nome,
			String cognome, String provincia, char sesso, Date datanascita,
			Set<Skill> personalSkill) {
			
    	try{
    		database.persist(new User(email, password, nome, cognome, provincia, sesso, datanascita));
    	}catch(PersistenceException e){
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
