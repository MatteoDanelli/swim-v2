/**
 * 
 */
package SE2.Swimv2.Session;

import java.util.List;
import javax.ejb.Remote;

import SE2.Swimv2.Entity.Skill;
import SE2.Swimv2.Entity.User;

/**
 * @author Matteo Danelli
 * Interfaccia per la gestione delle Skill.
 */
@Remote
public interface GestoreSkillRemote {
	void createSkill(String nome);
	List<Skill> getSkillSet();
	List<Skill> getPersonalSkill(User user);
}
