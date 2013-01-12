package SE2.Swimv2.Session;

import javax.ejb.Local;
import SE2.Swimv2.Exceptions.SkillException;

@Local
public interface GestoreSkillLocal {
	void creaSkill(String nome) throws SkillException;

}
