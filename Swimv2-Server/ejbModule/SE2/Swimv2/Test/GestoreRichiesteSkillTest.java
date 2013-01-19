package SE2.Swimv2.Test;

import static org.junit.Assert.*;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.junit.BeforeClass;
import org.junit.Test;

import SE2.Swimv2.Session.GestoreRichiesteAmiciziaRemote;
import SE2.Swimv2.Session.GestoreRichiesteSkillRemote;
/**
 * @author Matteo Danelli
 * Classe di test della classe GestoreRichiesteSkill
 *
 */
public class GestoreRichiesteSkillTest {
	
	static private Context jndiContext;
	static private GestoreRichiesteSkillRemote richiesteSkillRemote;	
	
	public GestoreRichiesteSkillTest() throws Exception {
		jndiContext= getInitialContext();
		Object ref = jndiContext.lookup("GestoreRichiesteSkill/remote");
		richiesteSkillRemote = (GestoreRichiesteSkillRemote) ref;
	}

	@Test
	public void testInviaRichiestaAggiuntaSkill() {
		fail("Not yet implemented");
	}

	@Test
	public void testAccettaRichiesta() {
		fail("Not yet implemented");
	}

	@Test
	public void testRifiutaRichiesta() {
		fail("Not yet implemented");
	}

	@Test
	public void testElencoRichieste() {
		fail("Not yet implemented");
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
