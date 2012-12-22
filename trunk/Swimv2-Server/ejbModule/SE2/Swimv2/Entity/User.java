package SE2.Swimv2.Entity;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.*;

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

	public User(String mail, String password, String nome, String cognome, String provincia, char sesso, Date data) {
		this.email=mail;
		this.nome=nome;
		this.cognome=cognome;
		setPassword(password);
		this.provincia=provincia;
		this.sesso=sesso;
		this.dataDiNascita=data;
	}

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
	private Date dataDiNascita;
	
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
	
	@ManyToMany (mappedBy="userCheLaPossiedono")
	private Set<Skill> skillPossedute;

	@ManyToMany 
	@JoinTable(name="AMICIZIA",
				joinColumns=@JoinColumn(name="USER_1",referencedColumnName="ID_USER"),
				inverseJoinColumns=@JoinColumn(name="USER_2", referencedColumnName="ID_USER")
            )   
	private Set<User> amici;
	
	//Getters and setters

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public char getSesso() {
		return sesso;
	}

	public void setSesso(char sesso) {
		this.sesso = sesso;
	}

	public Date getDataDiNascita() {
		return dataDiNascita;
	}

	public void setDataDiNascita(Date dataDiNascita) {
		this.dataDiNascita = dataDiNascita;
	}

	public long getId() {
		return id;
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

	public Set<User> getAmici() {
		return amici;
	}

	public void setAmici(Set<User> amici) {
		this.amici = amici;
	}
}
