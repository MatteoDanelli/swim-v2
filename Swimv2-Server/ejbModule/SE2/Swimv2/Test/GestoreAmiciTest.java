/**
 * 
 */
package SE2.Swimv2.Test;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.junit.BeforeClass;
import org.junit.Test;

import SE2.Swimv2.Entity.User;
import SE2.Swimv2.Exceptions.AmiciException;
import SE2.Swimv2.Exceptions.UserException;
import SE2.Swimv2.Session.GestoreAmiciRemote;
import SE2.Swimv2.Session.GestoreUserRemote;

/**
 * @author Matteo Danelli
 * Test della classe GestoreAmici
 */
public class GestoreAmiciTest {
	
	static private Context jndiContext;
	static private GestoreAmiciRemote amiciRemote;
	static private GestoreUserRemote userRemote;
	static long user1, user3, user2;
	
	@BeforeClass
	public static void runBeforeClass() throws NamingException, UserException, AmiciException {
		jndiContext= getInitialContext();
		Object ref1 = jndiContext.lookup("GestoreAmici/remote");
		Object ref2 = jndiContext.lookup("GestoreUser/remote");
		amiciRemote = (GestoreAmiciRemote) ref1;
		userRemote = (GestoreUserRemote) ref2;
		
		user1 = userRemote.addUser("user1@mail.com", "psw1", "Daniel", "Cantoni", "SO", 'M', new GregorianCalendar(), null);
		user2 = userRemote.addUser("user2@mail.com", "psw2", "Matteo", "Danelli", "BG", 'M', new GregorianCalendar(), null);
		user3 = userRemote.addUser("user3@mail.com", "psw", "Harry", "Potter", "HW", 'M', new GregorianCalendar(), null);
		amiciRemote.aggiungiAmicizia(user1, user3);
		amiciRemote.aggiungiAmicizia(user1, user2);
	}

	/**
	 * Test method for {@link SE2.Swimv2.Session.GestoreAmici#aggiungiAmicizia(long, long)}.
	 * @throws AmiciException 
	 */
	@Test
	public void testAggiungiAmicizia() throws AmiciException {
		assertEquals(2, amiciRemote.elencoAmici(user1).size());
		assertEquals(1, amiciRemote.elencoAmici(user2).size());
		assertEquals(1, amiciRemote.elencoAmici(user3).size());
		}

	/**
	 * Test method for {@link SE2.Swimv2.Session.GestoreAmici#elencoAmici(long)}.
	 * @throws AmiciException 
	 */
	@Test
	public void testElencoAmici() throws AmiciException {
		Set<User> elenco = amiciRemote.elencoAmici(user3);
		for (User u : elenco) {
			assertEquals("user1@mail.com", u.getEmail());
		}
	}

	/**
	 * Test method for {@link SE2.Swimv2.Session.GestoreAmici#verificaAmicizia(long, long)}.
	 * @throws AmiciException 
	 */
	@Test
	public void testVerificaAmicizia() throws AmiciException {
		assertTrue(amiciRemote.verificaAmicizia(user1, user2));
		assertTrue(amiciRemote.verificaAmicizia(user1, user3));
		assertTrue(amiciRemote.verificaAmicizia(user1, user2));
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
