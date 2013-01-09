package SE2.Swimv2.Session;

import java.util.List;
import javax.ejb.Remote;
import SE2.Swimv2.Entity.Skill;
import SE2.Swimv2.Exceptions.SkillException;

/**
 * @author Matteo Danelli
 * Interfaccia per la gestione delle Skill.
 */
@Remote
public interface GestoreSkillRemote {
	void creaSkill(String nome) throws SkillException;
	List<Skill> getSkillSet();
	List<Skill> getPersonalSkill(long userId);
	Skill getById(long Id);
}
