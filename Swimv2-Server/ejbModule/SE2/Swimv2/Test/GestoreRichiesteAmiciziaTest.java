package SE2.Swimv2.Test;

import static org.junit.Assert.*;

import java.util.GregorianCalendar;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.junit.BeforeClass;
import org.junit.Test;

import SE2.Swimv2.Exceptions.AmiciException;
import SE2.Swimv2.Exceptions.RichiestaAmiciziaException;
import SE2.Swimv2.Session.GestoreAmiciRemote;
import SE2.Swimv2.Session.GestoreRichiesteAmiciziaRemote;
import SE2.Swimv2.Session.GestoreUserRemote;
/**
 * @author Matteo Danelli
 * Classe di test della classe GestoreRichiesteAmicizia
 *
 */
public class GestoreRichiesteAmiciziaTest {
	
	static private Context jndiContext;
	static private GestoreRichiesteAmiciziaRemote richiesteAmiciziaRemote;	
	static private GestoreUserRemote userRemote;
	static private GestoreAmiciRemote amiciRemote;
	long u1,u2;
	
	public GestoreRichiesteAmiciziaTest() throws Exception {
		jndiContext= getInitialContext();
		Object ref1 = jndiContext.lookup("GestoreRichiesteAmicizia/remote");
		Object ref2 = jndiContext.lookup("GestoreUser/remote");
		Object ref3 = jndiContext.lookup("GestoreAmici/remote");
		amiciRemote = (GestoreAmiciRemote) ref3;
		userRemote = (GestoreUserRemote) ref2;
		richiesteAmiciziaRemote = (GestoreRichiesteAmiciziaRemote) ref1;
	}
	

	@Test
	public void testInviaRichiestaAmicizia() {
		try {
		u1 = userRemote.addUser("prova1@mail.test", "password", "u1", "cognome", "provincia", 'M', new GregorianCalendar(), null);
		u2 = userRemote.addUser("prova2@mail.test", "password", "u2", "cognome", "provincia", 'M', new GregorianCalendar(), null);
		richiesteAmiciziaRemote.inviaRichiestaAmicizia(u1, u2);
		assertEquals(1,richiesteAmiciziaRemote.elencoRichiesteAmicizia(u2).size());
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testAccettaRichiestaAmicizia() {
		try {
			richiesteAmiciziaRemote.accettaRichiestaAmicizia(richiesteAmiciziaRemote.elencoRichiesteAmicizia(u1).indexOf(0), u1);
			assertTrue(amiciRemote.verificaAmicizia(u2, u1));
		} catch (AmiciException e) {
			e.printStackTrace();
		} catch (RichiestaAmiciziaException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testRifiutaRichiestaAmicizia() {
		try {
			richiesteAmiciziaRemote.inviaRichiestaAmicizia(u1,u2);
			richiesteAmiciziaRemote.rifiutaRichiestaAmicizia(richiesteAmiciziaRemote.elencoRichiesteAmicizia(u1).indexOf(0), u1);
			assertFalse(amiciRemote.verificaAmicizia(u1, u2));
		} catch (RichiestaAmiciziaException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testEsisteRichiestaAmicizia() {
		try {
			richiesteAmiciziaRemote.inviaRichiestaAmicizia(u1,u2);
			assertTrue(richiesteAmiciziaRemote.esisteRichiestaAmicizia(u1, u2));
			assertFalse(richiesteAmiciziaRemote.esisteRichiestaAmicizia(u2, u1));
		} catch (RichiestaAmiciziaException e) {
			e.printStackTrace();
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
