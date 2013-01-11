/**
 * 
 */
package SE2.Swimv2.Test;

import static org.junit.Assert.*;

import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import SE2.Swimv2.Entity.Feedback;
import SE2.Swimv2.Exceptions.FeedbackException;
import SE2.Swimv2.Session.GestoreAmiciRemote;
import SE2.Swimv2.Session.GestoreFeedbackRemote;
import SE2.Swimv2.Session.GestoreUserRemote;

/**
 * @author Matteo Danelli
 * Test della classe GestoreFeedback
 */
public class GestoreFeedbackTest {
	
	static private Context jndiContext;
	static private GestoreFeedbackRemote feedbackRemote;
	static private GestoreUserRemote userRemote;
	static private GestoreAmiciRemote amiciRemote;
	static long user1, user2, user3;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		jndiContext= getInitialContext();
		Object ref1 = jndiContext.lookup("GestoreFeedback/remote");
		Object ref2 = jndiContext.lookup("GestoreUser/remote");
		Object ref3 = jndiContext.lookup("GestoreAmici/remote");
		amiciRemote = (GestoreAmiciRemote) ref3;
		feedbackRemote = (GestoreFeedbackRemote) ref1;
		userRemote = (GestoreUserRemote) ref2;
		
		user1 = userRemote.addUser("user4@mail.com", "psw1", "Daniele", "Cantoni", "SO", 'M', new GregorianCalendar(), null);
		user2 = userRemote.addUser("user5@mail.com", "psw2", "Matthews", "Danelli", "BG", 'M', new GregorianCalendar(), null);
		user3 = userRemote.addUser("user6@mail.com", "psw", "George", "Clooney", "HW", 'M', new GregorianCalendar(), null);	
	
		amiciRemote.aggiungiAmicizia(user1, user3);
		amiciRemote.aggiungiAmicizia(user1, user2);
		
		feedbackRemote.creaFeedback(user1, user3, 5, "Commento positivo");
		feedbackRemote.creaFeedback(user2, user1, 2, "Commento negativo");
		feedbackRemote.creaFeedback(user3, user1, 4, "Commento positivo");
	}

	/**
	 * Test method for {@link SE2.Swimv2.Session.GestoreFeedback#creaFeedback(long, long, int, java.lang.String)}.
	 * @throws FeedbackException 
	 */
	@Test
	public void testCreaFeedback() throws FeedbackException {
		
		try {
			feedbackRemote.creaFeedback(user3, user2, 4, "Commento positivo");
			Assert.fail("No exception thrown: user non amici");
		}
		catch (FeedbackException e) {
		}
		
		try {
			feedbackRemote.creaFeedback(user2, user2, 4, "Commento positivo");
			Assert.fail("No exception thrown: auto-assegnazione");
		}
		catch (FeedbackException e) {
		}
		
		try {
			feedbackRemote.creaFeedback(user1, user2, 8, "Molto positivo!");
			Assert.fail("No exception thrown: feedback non consentito");
		}
		catch (FeedbackException e) {
		}
		
		List<Feedback> feedback1 = feedbackRemote.elencoFeedback(user1);
		List<Feedback> feedback2 = feedbackRemote.elencoFeedback(user2);
		List<Feedback> feedback3 = feedbackRemote.elencoFeedback(user3);
		
		assertEquals(2, feedback1.size());
		assertEquals(0, feedback2.size());
		assertEquals(1, feedback3.size());
	}

	/**
	 * Test method for {@link SE2.Swimv2.Session.GestoreFeedback#elencoFeedback(long)}.
	 * @throws FeedbackException 
	 */
	@Test
	public void testElencoFeedback() throws FeedbackException {

		List<Feedback> feedback = feedbackRemote.elencoFeedback(user1);
		assertEquals(2, feedback.size());
	}

	/**
	 * Test method for {@link SE2.Swimv2.Session.GestoreFeedback#mediaVotiFeedback(long)}.
	 * @throws FeedbackException 
	 */
	@Test
	public void testMediaVotiFeedback() throws FeedbackException {
		assertEquals(3, feedbackRemote.mediaVotiFeedback(user1), 0);
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
