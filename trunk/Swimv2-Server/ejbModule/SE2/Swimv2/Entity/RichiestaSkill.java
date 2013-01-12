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
	
	@Column(name="SKILL_RICHIESTA",nullable=false)
	private String skillRichiesta;
	
	@ManyToOne
	@JoinColumn(name="MITTENTE", nullable=false)
	private User mittente;

	//Default constructor
	public RichiestaSkill(){
		
	}
	
	public RichiestaSkill(User mittente, String skill) {
		this.mittente=mittente;
		this.skillRichiesta=skill;
	}
	//Getters

	public String getSkillRichiesta() {
		return skillRichiesta;
	}

	public User getMittente() {
		return mittente;
	}

	public long getId() {
		return id;
	}

}
