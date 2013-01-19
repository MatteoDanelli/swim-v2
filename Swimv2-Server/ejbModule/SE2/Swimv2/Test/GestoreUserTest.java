package SE2.Swimv2.Test;

import static org.junit.Assert.*;

import java.util.GregorianCalendar;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.junit.Test;

import SE2.Swimv2.Exceptions.UserException;
import SE2.Swimv2.Session.GestoreUserRemote;
/**
 * @author Matteo Danelli
 * Classe di test della classe GestoreUser
 *
 */
public class GestoreUserTest {
	
	static private Context jndiContext;
	static private GestoreUserRemote userRemote;	
	
	public GestoreUserTest() throws Exception {
		jndiContext= getInitialContext();
		Object ref = jndiContext.lookup("GestoreUser/remote");
		userRemote = (GestoreUserRemote) ref;
	}

	@Test
	public void testAddUser() {
		try {
			userRemote.addUser("prova1@mail.test", "password", "u1", "cognome", "provincia", 'M', new GregorianCalendar(), null);
		}
		catch (UserException e) {
			e.printStackTrace();
		}
		assertEquals("prova1@mail.test", userRemote.getByEmail("prova1@mail.test").getEmail());

	}

	/**
	 * Ottiene il Context utilizzato per ottenere i riferimenti ai RemoteSystems
	 * @return Context utilizzato per la lookup
	 * @throws NamingException Eccezione lanciata nel caso il metodo non riesca ad ottenere il Context
	 */
	static public Context getInitialContext() throws NamingException {
		
		Hashtable<String,String> env = new Hashtable<String,String>();
	
		env.put(Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
		env.put(Context.PROVIDER_URL, "localhost:1099");	
		return new InitialContext(env);		
	}
}
