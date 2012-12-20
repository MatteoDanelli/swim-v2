/**
 * 
 */
package SE2.Swimv2.Entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.*;

/**
 * @author Matteo Danelli
 * Tale classe modella l'entità Richiesta Skill, ed è associata opzionalmente agli User.
 */
@Entity
@Table(name="RICHIESTA_SKILL")
public class RichiestaSkill implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID_RICHIESTA_SKILL")
	private long id;
	
	@Column(name="LETTA")
	private boolean richiestaLetta;
	
	@Column(name="SKILL_RICHIESTA",nullable=false)
	private String skillRichiesta;
	
	@ManyToOne
	@JoinColumn(name="MITTENTE", nullable=false)
	private User mittente;

	/**
	 * @return the richiestaLetta
	 */
	public boolean isRichiestaLetta() {
		return richiestaLetta;
	}

	/**
	 * @param richiestaLetta the richiestaLetta to set
	 */
	public void setRichiestaLetta(boolean richiestaLetta) {
		this.richiestaLetta = richiestaLetta;
	}

	/**
	 * @return the skillRichiesta
	 */
	public String getSkillRichiesta() {
		return skillRichiesta;
	}

	/**
	 * @param skillRichiesta the skillRichiesta to set
	 */
	public void setSkillRichiesta(String skillRichiesta) {
		this.skillRichiesta = skillRichiesta;
	}

	/**
	 * @return the mittente
	 */
	public User getMittente() {
		return mittente;
	}

	/**
	 * @param mittente the mittente to set
	 */
	public void setMittente(User mittente) {
		this.mittente = mittente;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

}
