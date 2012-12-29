/**
 * 
 */
package SE2.Swimv2.Session;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import SE2.Swimv2.Entity.User;
import SE2.Swimv2.Entity.Skill;

/**
 * @author Matteo Danelli
 *
 */
public class GestoreSkill implements GestoreSkillRemote {
	@PersistenceContext(unitName = "Swimv2")
	EntityManager database;
	
	@Override
	public List<Skill> getSkillSet() {
		List<Skill> skillSet;
		Query q = database.createQuery("FROM Skill");
		skillSet = (List<Skill>) q.getResultList();
		return skillSet;
	}

	@Override
	public List<Skill> getPersonalSkill(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createSkill(String nome) {
		database.persist(new Skill(nome));
	}

}
