/**
 * 
 */
package SE2.Swimv2.Entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;

/**
 * @author Matteo Danelli
 * Tale classe modella le Skill. E' in relazione molti a molti con User tramite una tabella intermedia.
 */
@Entity
@Table(name="SKILL")
public class Skill implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public Skill(String nome) {
		this.nome=nome;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID_SKILL")
	private long id;
	
	@Column(name="NOME")
	private String nome;

	@ManyToMany
	@JoinTable(name="USER_SKILL", 
				joinColumns=@JoinColumn(name="SKILL_ID"),
				inverseJoinColumns=@JoinColumn(name="USER_ID"))
	private Set<User> UserCheLaPossiedono;

	//Getters and setters
	public long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public Set<User> getUserCheLaPossiedono() {
		return UserCheLaPossiedono;
	}

}
