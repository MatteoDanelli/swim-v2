package SE2.Swimv2.Session;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import SE2.Swimv2.Entity.RichiestaSkill;
import SE2.Swimv2.Entity.Skill;
import SE2.Swimv2.Entity.User;
import SE2.Swimv2.Exceptions.RichiesteSkillException;

/**
 * Session Bean implementation class GestoreRichiesteSkill
 */
@Stateless
public class GestoreRichiesteSkill implements GestoreRichiesteSkillRemote {
	@PersistenceContext(unitName = "Swimv2")
	EntityManager database;

	@SuppressWarnings("unchecked")
	@Override
	public List<RichiestaSkill> elencoRichieste() {
		Query q = database.createQuery("FROM RichiestaSkill");
		List<RichiestaSkill> elenco = q.getResultList();
		return elenco;
	}

	@Override
	public void settaComeLetta(RichiestaSkill richiestaSkillLetta) {
		RichiestaSkill richiesta = database.find(RichiestaSkill.class, richiestaSkillLetta);
		richiesta.setRichiestaLetta(true);
	}

	@Override
	public void accettaRichiesta(long skill) throws RichiesteSkillException {
		try {
		RichiestaSkill skillDaAccettare = database.find(RichiestaSkill.class, skill);
		database.remove(skillDaAccettare);
		String nomeSkill = skillDaAccettare.getSkillRichiesta();
		Skill nuovaSkill = new Skill(nomeSkill);
		database.persist(nuovaSkill);
		}
		catch (Exception e) {
			throw new RichiesteSkillException("Errore nell'accettare la richiesta!");
		}
	}

	@Override
	public void rifiutaRichiesta(long skill) throws RichiesteSkillException {
		try {
			RichiestaSkill skillDaRifiutare = database.find(RichiestaSkill.class, skill);
			database.remove(skillDaRifiutare);
		}
		catch (PersistenceException e) {
			throw new RichiesteSkillException("Errore nel rifiuto della skill!");
		}
	}

	@Override
	public void inviaRichiestaAggiuntaSkill(long userCheLaRichiede,	String skillRichiesta) throws RichiesteSkillException {
		try {
			User userMittente = database.find(User.class, userCheLaRichiede);
			RichiestaSkill nuovaRichiesta = new RichiestaSkill(userMittente, skillRichiesta);
			database.persist(nuovaRichiesta);	
		}
		catch (Exception e) {
			throw new RichiesteSkillException("Errore nell'invio della richiesta!");
		}
	
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public int numeroDiNuoveRichieste() {
		Query q = database.createQuery("FROM RichiestaSkill");
		List<RichiestaSkill> result = q.getResultList();
		return result.size();
		
	}

}
