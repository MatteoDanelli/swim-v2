package SE2.Swimv2.Session;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import SE2.Swimv2.Entity.User;
import SE2.Swimv2.Entity.Skill;
import SE2.Swimv2.Exceptions.SkillException;

@Stateless
public class GestoreSkill implements GestoreSkillRemote {
	@PersistenceContext(unitName = "Swimv2")
	EntityManager database;

	@Override
	public void creaSkill(String nome) throws SkillException {
		
		//verifico il nome non sia vuoto
		if(nome==null || nome.equals("")){
			throw new SkillException("nome skill non valido");
		}
		
		Skill skill = new Skill(nome);
		
		try{
			database.persist(skill);
		}
		catch(PersistenceException e){
			throw new SkillException("Skill non inserita");
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Skill> getSkillSet() {

		Query q = database.createQuery("FROM Skill s ORDER BY s.nome asc");

		List<Skill> skillSet = (List<Skill>) q.getResultList();
		return skillSet;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Skill> getPersonalSkill(long userId){
		User user = database.find(User.class, userId);
		Query q = database.createQuery("FROM Skill s WHERE :user in elements(s.UserCheLaPossiedono) ORDER BY s.nome asc");
		q.setParameter("user", user);
		
		List<Skill> skillSet = (List<Skill>) q.getResultList();
		return skillSet;

	}
}
