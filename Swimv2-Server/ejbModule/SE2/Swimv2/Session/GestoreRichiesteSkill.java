package SE2.Swimv2.Session;

import java.util.Set;

import javax.ejb.Stateless;

import SE2.Swimv2.Entity.RichiestaSkill;
import SE2.Swimv2.Entity.Skill;
import SE2.Swimv2.Entity.User;

/**
 * Session Bean implementation class GestoreRichiesteSkill
 */
@Stateless
public class GestoreRichiesteSkill implements GestoreRichiesteSkillRemote {

    /**
     * Default constructor. 
     */
    public GestoreRichiesteSkill() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public Set<RichiestaSkill> elencoRichieste() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void settaComeLetta(RichiestaSkill skillLetta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void accettaRichiesta(RichiestaSkill skillDaAccettare) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rifiutaRichiesta(RichiestaSkill skillDaRifiutare) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void inviaRichiestaAggiuntaSkill(User userCheLaRichiede,
			Skill skillRichiesta) {
		// TODO Auto-generated method stub
		
	}

}
