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
		long idAdmin=0;
			try {
				idAdmin=adminRemote.createAdmin("prova@mail.test", "prova");
			} catch (AdminException e) {
			}
			assertEquals("prova@mail.test", adminRemote.getAdmin(idAdmin).getEmail());
		}

	/**
	 * Test method for {@link SE2.Swimv2.Session.GestoreAdmin#modificaPassword(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testModificaPassword() {
		long idAdmin=0;
		try {
			idAdmin=adminRemote.createAdmin("admin", "admin");
		} catch (AdminException e) {
		}
		
		try {
			adminRemote.modificaPassword(idAdmin, "nuovaPassword");
		} catch (AdminException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Admin a = adminRemote.getAdmin(idAdmin);
			assertEquals("nuovaPassword", a.getPassword());
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
