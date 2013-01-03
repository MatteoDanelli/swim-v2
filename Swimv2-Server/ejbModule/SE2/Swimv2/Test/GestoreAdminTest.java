/**
 * 
 */
package SE2.Swimv2.Test;

import static org.junit.Assert.*;

import java.util.Hashtable;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;


import org.junit.Test;

import SE2.Swimv2.Session.GestoreAdminRemote;
import SE2.Swimv2.Entity.Admin;
import SE2.Swimv2.Exceptions.AdminException;

/**
 * @author Matteo Danelli
 * Classe di test della classe GestoreAdmin
 *
 */
public class GestoreAdminTest {

	static private Context jndiContext;
	static private GestoreAdminRemote adminRemote;	
	
	public GestoreAdminTest() throws Exception {
		jndiContext= getInitialContext();
		Object ref = jndiContext.lookup("GestoreAdmin/remote");
		adminRemote = (GestoreAdminRemote) ref;
	}

	/**
	 * Test method for {@link SE2.Swimv2.Session.GestoreAdmin#createAdmin(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testCreateAdmin() {
			try {
				adminRemote.createAdmin("prova@mail.test", "prova");
			} catch (AdminException e) {
				e.printStackTrace();
			}
			assertEquals(1, adminRemote.getAdmin().size());
		}

	/**
	 * Test method for {@link SE2.Swimv2.Session.GestoreAdmin#modificaPassword(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testModificaPassword() {
		adminRemote.modificaPassword("prova@mail.test", "nuovaPassword");
		for (Admin a : adminRemote.getAdmin()) {
			assertEquals("nuovaPassword", a.getPassword());
		}
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
