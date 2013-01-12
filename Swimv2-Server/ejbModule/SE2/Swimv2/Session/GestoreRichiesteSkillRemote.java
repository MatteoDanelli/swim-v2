/**
 * 
 */
package SE2.Swimv2.Session;

import java.util.List;
import javax.ejb.Remote;
import SE2.Swimv2.Entity.RichiestaSkill;
import SE2.Swimv2.Exceptions.RichiesteSkillException;

/**
 * @author Matteo Danelli
 * Interfaccia con i metodi per gestire le richieste di ampliamento skill set.
 */
@Remote
public interface GestoreRichiesteSkillRemote {

	void inviaRichiestaAggiuntaSkill(long userCheLaRichiede, String skillRichiesta) throws RichiesteSkillException;
	void accettaRichiesta(long skill) throws RichiesteSkillException;
	void rifiutaRichiesta(long skill) throws RichiesteSkillException;
	List<RichiestaSkill> elencoRichieste();

}
