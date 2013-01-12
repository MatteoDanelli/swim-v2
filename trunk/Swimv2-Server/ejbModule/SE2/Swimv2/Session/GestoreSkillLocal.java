package SE2.Swimv2.Session;

import javax.ejb.Local;
import SE2.Swimv2.Exceptions.SkillException;

@Local
public interface GestoreSkillLocal {
	public void creaSkill(String nome) throws SkillException;
	public boolean isPresente(String skill);

}
