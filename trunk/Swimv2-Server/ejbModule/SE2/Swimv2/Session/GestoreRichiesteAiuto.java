package SE2.Swimv2.Session;

import java.util.Set;

import javax.ejb.Stateless;

import SE2.Swimv2.Entity.Messaggio;
import SE2.Swimv2.Entity.User;

/**
 * Session Bean implementation class GestoreRichiesteAiuto
 */
@Stateless
public class GestoreRichiesteAiuto implements GestoreRichiesteAiutoRemote {

    /**
     * Default constructor. 
     */
    public GestoreRichiesteAiuto() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void inviaRichiestaAiuto(User mittente, User destinatario,
			String testo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Set<Messaggio> RichiesteAiuto(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean verificaNuoveRichiesteAiuto(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void settaRichiestaLetta(Messaggio messaggio) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Messaggio getById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
