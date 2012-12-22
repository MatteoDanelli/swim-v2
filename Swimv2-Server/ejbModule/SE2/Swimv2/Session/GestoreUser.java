package SE2.Swimv2.Session;

import java.util.Date;
import java.util.Set;

import javax.ejb.Stateless;

import SE2.Swimv2.Entity.Skill;
import SE2.Swimv2.Entity.User;

/**
 * Session Bean implementation class GestoreUser
 */
@Stateless
public class GestoreUser implements GestoreUserRemote {

    /**
     * Default constructor. 
     */
    public GestoreUser() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public User addUser(String email, String password, String nome,
			String cognome, String provincia, char sesso, Date datanascita,
			Set<Skill> personalSkill) {
		// TODO Auto-generated method stub
		return null;
	}

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
