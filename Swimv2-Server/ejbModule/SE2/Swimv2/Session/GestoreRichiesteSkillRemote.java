/**
 * 
 */
package SE2.Swimv2.Session;

import java.util.Set;

import javax.ejb.Remote;

import SE2.Swimv2.Entity.RichiestaSkill;
import SE2.Swimv2.Entity.Skill;
import SE2.Swimv2.Entity.User;

/**
 * @author Matteo Danelli
 * Interfaccia con i metodi per gestire le richieste di ampliamento skill set.
 */
@Remote
public interface GestoreRichiesteSkillRemote {
	Set<RichiestaSkill> elencoRichieste();
	void settaComeLetta(RichiestaSkill skillLetta);
	void accettaRichiesta(RichiestaSkill skillDaAccettare);
	void rifiutaRichiesta(RichiestaSkill skillDaRifiutare);
	void inviaRichiestaAggiuntaSkill(User userCheLaRichiede, Skill skillRichiesta);
	
}
