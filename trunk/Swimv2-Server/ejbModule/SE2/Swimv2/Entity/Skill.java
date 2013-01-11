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

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID_SKILL")
	private long id;
	
	@Column(name="NOME",unique=true)
	private String nome;

	@ManyToMany (mappedBy="skillPossedute")				
	private Set<User> UserCheLaPossiedono;

	public Skill() {
	}	
	
	public Skill(String nome) {
		this.nome=nome;
	}
	
	//Getters
	public long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public Set<User> getUserCheLaPossiedono() {
		return UserCheLaPossiedono;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if (obj instanceof Skill) {
			Skill other= (Skill)obj;
			if(this.getId()==other.getId()){
				return true;
			}
		}
		return false;
	}

}
