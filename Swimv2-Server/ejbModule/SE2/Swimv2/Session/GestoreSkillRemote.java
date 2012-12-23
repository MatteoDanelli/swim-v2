/**
 * 
 */
package SE2.Swimv2.Session;

import java.util.Set;

import javax.ejb.Remote;

import SE2.Swimv2.Entity.Skill;
import SE2.Swimv2.Entity.User;

/**
 * @author Matteo Danelli
 * Interfaccia per la gestione dell'entità Skill
 */
@Remote
public interface GestoreSkillRemote {
	Set<Skill> getSkillSet();
	Set<Skill> getPersonalSkill(User user);
}
