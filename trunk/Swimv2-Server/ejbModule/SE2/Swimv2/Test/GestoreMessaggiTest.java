package SE2.Swimv2.Test;

import static org.junit.Assert.*;

import java.util.GregorianCalendar;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.junit.Test;

import SE2.Swimv2.Session.GestoreMessaggiRemote;
import SE2.Swimv2.Session.GestoreUserRemote;
/**
 * @author Matteo Danelli
 * Classe di test della classe GestoreMessaggi
 *
 */
public class GestoreMessaggiTest {
	
	static private Context jndiContext;
	static private GestoreMessaggiRemote messaggiRemote;
	static private GestoreUserRemote userRemote;
	static private long u1, u2;
	
	public GestoreMessaggiTest() throws Exception {
		jndiContext= getInitialContext();
		Object ref1 = jndiContext.lookup("GestoreMessaggi/remote");
		Object ref2 = jndiContext.lookup("GestoreUser/remote");
		userRemote = (GestoreUserRemote) ref2;
		messaggiRemote = (GestoreMessaggiRemote) ref1;
	}

	@Test
	public void testInviaMessaggio() {
		try {
			u1 = userRemote.addUser("prova1@mail.test", "password", "u1", "cognome", "provincia", 'M', new GregorianCalendar(), null);
			u2 =userRemote.addUser("prova2@mail.test", "password", "u2", "cognome", "provincia", 'M', new GregorianCalendar(), null);
			messaggiRemote.inviaMessaggio(u1, u2, "testo");
			assertEquals(1,messaggiRemote.elencoMessaggi(u2).size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testVerificaNuoviMessaggi() {
		try {
			messaggiRemote.inviaMessaggio(u1, u2, "testo");
			assertFalse(messaggiRemote.verificaNuoviMessaggi(u1)!=0);
			messaggiRemote.settaMessaggioLetto(messaggiRemote.elencoMessaggi(u1).indexOf(0));
			assertTrue(messaggiRemote.verificaNuoviMessaggi(u1)==0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}	}

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
