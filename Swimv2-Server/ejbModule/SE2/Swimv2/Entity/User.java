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
 * E' in relazione con le richieste skill, messaggio, Skill, feedback, richieste amicizie e amicizie.
 * E' l'entità centrale del sistema.
 */

@Entity
@Table(name="USER")
public class User implements Serializable {

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
	private String sesso;
	
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

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return the cognome
	 */
	public String getCognome() {
		return cognome;
	}

	/**
	 * @param cognome the cognome to set
	 */
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	/**
	 * @return the provincia
	 */
	public String getProvincia() {
		return provincia;
	}

	/**
	 * @param provincia the provincia to set
	 */
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	/**
	 * @return the sesso
	 */
	public String getSesso() {
		return sesso;
	}

	/**
	 * @param sesso the sesso to set
	 */
	public void setSesso(String sesso) {
		this.sesso = sesso;
	}

	/**
	 * @return the dataDiNascita
	 */
	public Date getDataDiNascita() {
		return dataDiNascita;
	}

	/**
	 * @param dataDiNascita the dataDiNascita to set
	 */
	public void setDataDiNascita(Date dataDiNascita) {
		this.dataDiNascita = dataDiNascita;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @return the richiesteSkill
	 */
	public Set<RichiestaSkill> getRichiesteSkill() {
		return richiesteSkill;
	}

	/**
	 * @return the richiesteAmiciziaInviate
	 */
	public Set<RichiestaAmicizia> getRichiesteAmiciziaInviate() {
		return richiesteAmiciziaInviate;
	}

	/**
	 * @return the richiesteAmiciziaRicevute
	 */
	public Set<RichiestaAmicizia> getRichiesteAmiciziaRicevute() {
		return richiesteAmiciziaRicevute;
	}

	/**
	 * @return the feedbackInviati
	 */
	public Set<Feedback> getFeedbackInviati() {
		return feedbackInviati;
	}

	/**
	 * @return the feedbackRicevuti
	 */
	public Set<Feedback> getFeedbackRicevuti() {
		return feedbackRicevuti;
	}
}
