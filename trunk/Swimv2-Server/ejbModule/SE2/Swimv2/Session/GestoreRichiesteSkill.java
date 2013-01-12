package SE2.Swimv2.Session;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import SE2.Swimv2.Entity.RichiestaSkill;
import SE2.Swimv2.Entity.User;
import SE2.Swimv2.Exceptions.RichiesteSkillException;
import SE2.Swimv2.Exceptions.SkillException;

/**
 * Session Bean implementation class GestoreRichiesteSkill
 */
@Stateless
public class GestoreRichiesteSkill implements GestoreRichiesteSkillRemote {
	@PersistenceContext(unitName = "Swimv2")
	EntityManager database;
	@EJB
	private GestoreSkillLocal gestoreSkill;

	/**
	 * Questo metodo invia una nuova richiesta di aggiunta skill
	 */
	@Override
	public void inviaRichiestaAggiuntaSkill(long userCheLaRichiede,	String skillRichiesta) throws RichiesteSkillException {
		
		if(gestoreSkill.isPresente(skillRichiesta)){
			throw new RichiesteSkillException("Errore, richiesta non effettuats");
		}
		
		User userMittente = database.find(User.class, userCheLaRichiede);
		try {			
			RichiestaSkill nuovaRichiesta = new RichiestaSkill(userMittente, skillRichiesta);
			database.persist(nuovaRichiesta);	
			return;
		}
		catch (PersistenceException e) {				
		}catch(NullPointerException e){			
		}
		throw new RichiesteSkillException("Errore, richiesta non effettuats");
	
	}
	
	/**
	 * Questo metodo accetta una richiesta skill
	 */
	@Override
	public void accettaRichiesta(long skill) throws RichiesteSkillException {
		
		RichiestaSkill skillDaAccettare = database.find(RichiestaSkill.class, skill);
		String nuovaSkill = skillDaAccettare.getSkillRichiesta();
		
		try {
			database.remove(skillDaAccettare);
			database.flush();
		}
		catch (PersistenceException e) {
			throw new RichiesteSkillException("Errore nell'accettare la richiesta!");
		}catch(NullPointerException e){
			throw new RichiesteSkillException("Errore nell'accettare la richiesta!");
		}
		
		try {
			gestoreSkill.creaSkill(nuovaSkill);
		} catch (SkillException e) {
			throw new RichiesteSkillException("Errore nell'accettare la richiesta!");
		}
		
	}

	/**
	 * Questo metodo rifiuta una richiesta ai aggiunta skill, ovvero la cancella dal Database
	 */
	@Override
	public void rifiutaRichiesta(long skill) throws RichiesteSkillException {
		
		RichiestaSkill skillDaRifiutare = database.find(RichiestaSkill.class, skill);
		try {
			database.remove(skillDaRifiutare);
			database.flush();
			return;
		}
		catch (PersistenceException e) {				
		}catch(NullPointerException e){			
		}
		throw new RichiesteSkillException("Errore nel rifiuto della skill!");
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public List<RichiestaSkill> elencoRichieste() {
		Query q = database.createQuery("FROM RichiestaSkill r ORDER BY r.skillRichiesta asc");
		List<RichiestaSkill> elenco = q.getResultList();
		return elenco;
	}
	


}
