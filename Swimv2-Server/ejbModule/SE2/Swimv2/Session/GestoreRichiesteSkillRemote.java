/**
 * 
 */
package SE2.Swimv2.Session;

import java.util.List;
import javax.ejb.Remote;
import SE2.Swimv2.Entity.RichiestaSkill;

/**
 * @author Matteo Danelli
 * Interfaccia con i metodi per gestire le richieste di ampliamento skill set.
 */
@Remote
public interface GestoreRichiesteSkillRemote {
	List<RichiestaSkill> elencoRichieste();
	void settaComeLetta(RichiestaSkill skillLetta);
	void accettaRichiesta(RichiestaSkill skillDaAccettare);
	void rifiutaRichiesta(RichiestaSkill skillDaRifiutare);
	void inviaRichiestaAggiuntaSkill(long userCheLaRichiede, String skillRichiesta);
	int numeroDiNuoveRichieste();

}
