package SE2.Swimv2.Session;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import SE2.Swimv2.Entity.RichiestaSkill;
import SE2.Swimv2.Entity.Skill;
import SE2.Swimv2.Entity.User;

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
	public void accettaRichiesta(RichiestaSkill skillDaAccettare) {
		database.remove(skillDaAccettare);
		String nomeSkill = skillDaAccettare.getSkillRichiesta();
		Skill nuovaSkill = new Skill(nomeSkill);
		database.persist(nuovaSkill);
	}

	@Override
	public void rifiutaRichiesta(RichiestaSkill skillDaRifiutare) {
		database.remove(skillDaRifiutare);
	}

	@Override
	public void inviaRichiestaAggiuntaSkill(long userCheLaRichiede,	String skillRichiesta) {
		User userMittente = database.find(User.class, userCheLaRichiede);
		RichiestaSkill nuovaRichiesta = new RichiestaSkill(userMittente, skillRichiesta);
		database.persist(nuovaRichiesta);		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public int numeroDiNuoveRichieste() {
		Query q = database.createQuery("FROM RichiestaSkill");
		List<RichiestaSkill> result = q.getResultList();
		System.out.println(result.size());
		return result.size();
		
	}

}
