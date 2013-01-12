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
	public void creaSkill(String nome) throws SkillException;
	public List<Skill> getSkillSet();
	public List<Skill> getPersonalSkill(long userId);
	public Skill getById(long Id);
}
