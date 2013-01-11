package SE2.Swimv2.Entity;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * 
 */

/**
 * @author Matteo Danelli
 * Questa classe modella l'user del sistema.
 * E' in relazione con le richieste skill, messaggio, skill, feedback, richieste amicizie.
 * E' l'entità centrale del sistema.
 * NOTA: la relazione amicizia è modellata con una relazione N:N con sè stessa.
 */

@Entity
@Table(name="USER")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID_USER")
	private long id;
	
	@Column(name="EMAIL", unique=true, nullable=false)
	private String email;
	
	@Column(name="PASSWORD", nullable=false)
	private String password;
	
	@Column(name="NOME", nullable=false)
	private String nome;
	
	@Column(name="COGNOME", nullable=false)
	private String cognome;
	
	@Column(name="PROVINCIA", nullable=true)
	private String provincia;
	
	@Column(name="SESSO", nullable=true)
	private char sesso;
	
	@Column(name="DATA_DI_NASCITA", nullable=true)
	private Calendar dataDiNascita;
	
	
	@OneToMany(mappedBy="mittente")
	private Set<RichiestaSkill> richiesteSkill;
	
	@OneToMany(mappedBy="mittente")
	private Set<RichiestaAmicizia> richiesteAmiciziaInviate;
	
	@OneToMany(mappedBy="destinatario")
	private Set<RichiestaAmicizia> richiesteAmiciziaRicevute;
	
	@OneToMany(mappedBy="mittente")
	private Set<Feedback> feedbackInviati;
	
	@OneToMany (mappedBy="destinatario")
	private Set<Feedback> feedbackRicevuti;
	
	@ManyToMany
	@JoinTable(name="USER_SKILL", 
				joinColumns=@JoinColumn(name="USER_ID",referencedColumnName="ID_USER"),
				inverseJoinColumns=@JoinColumn(name="SKILL_ID", referencedColumnName="ID_SKILL"))
	private Set<Skill> skillPossedute;
	
	@ManyToMany 
	@JoinTable(name="AMICIZIA",
				joinColumns=@JoinColumn(name="USER_1_ID",referencedColumnName="ID_USER"),
				inverseJoinColumns=@JoinColumn(name="USER_2_ID", referencedColumnName="ID_USER")
            )
	private Set<User> amici1;
		
	@ManyToMany 
	@JoinTable(name="AMICIZIA",
				joinColumns=@JoinColumn(name="USER_2_ID",referencedColumnName="ID_USER"),
				inverseJoinColumns=@JoinColumn(name="USER_1_ID", referencedColumnName="ID_USER")
            )
	private Set<User> amici2;
	
	public User(){
	};
	
	public User(String mail, String password, String nome, String cognome, String provincia, char sesso, Calendar data,Set<Skill> skillPossedute) {
		this.email=mail;
		this.nome=nome;
		this.cognome=cognome;
		this.password=password;
		this.provincia=provincia;
		this.sesso=sesso;
		this.dataDiNascita=data;
		this.skillPossedute = skillPossedute;
	}
	
	//getter
	public long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public String getNome() {
		return nome;
	}

	public String getCognome() {
		return cognome;
	}

	public String getProvincia() {
		return provincia;
	}

	public char getSesso() {
		return sesso;
	}

	public Calendar getDataDiNascita() {
		return dataDiNascita;
	}

	public Set<RichiestaSkill> getRichiesteSkill() {
		return richiesteSkill;
	}

	public Set<RichiestaAmicizia> getRichiesteAmiciziaInviate() {
		return richiesteAmiciziaInviate;
	}

	public Set<RichiestaAmicizia> getRichiesteAmiciziaRicevute() {
		return richiesteAmiciziaRicevute;
	}

	public Set<Feedback> getFeedbackInviati() {
		return feedbackInviati;
	}

	public Set<Feedback> getFeedbackRicevuti() {
		return feedbackRicevuti;
	}

	public Set<Skill> getSkillPossedute() {
		return skillPossedute;
	}

	public Set<User> getAmici1() {
		return amici1;
	}

	public Set<User> getAmici2() {
		return amici2;
	}

	//setter
	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public void setSesso(char sesso) {
		this.sesso = sesso;
	}

	public void setDataDiNascita(Calendar dataDiNascita) {
		this.dataDiNascita = dataDiNascita;
	}

	public void setSkillPossedute(Set<Skill> skillPossedute) {
		this.skillPossedute = skillPossedute;
	}

	// ADD per relazione many to many
	public void addAmico(User user){
		this.amici1.add(user);
	}

	@Override
	public boolean equals(Object obj) {
		
		if (obj instanceof User) {
			User other= (User)obj;
			if(this.getId()==other.getId()){
				return true;
			}
		}
		return false;
	}

}
