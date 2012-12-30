/**
 * 
 */
package SE2.Swimv2.Entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Matteo Danelli 
 * Tale classe rappresenta i messaggi all'interno del sistema, siano essi richieste di aiuto che conversazioni tra user.
 */
@Entity
@Table(name="MESSAGGIO")

public class Messaggio implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID_MESSAGGIO")
	private long id;
	
	@ManyToOne
	@JoinColumn(name="MITTENTE", nullable=false)
	private User mittente;
	
	@ManyToOne
	@JoinColumn(name="DESTINATARIO", nullable=false)
	private User destinatario;
	
	@ManyToOne
	@JoinColumn(name="SKILL_RICHIESTA", nullable=true)
	private Skill skillRichiesta;
	
	@Column(name="MESSAGGIO_LETTO", nullable=true)
	private boolean isMessaggioLetto;
	
	@Column(name="RICHIESTA_DI_AIUTO", nullable=true)
	private boolean isRichiestaAiuto;
	
	@Column(name="DATA_DI_INVIO", nullable=false)
	private Date dataInvio;
	
	@Column(name="TESTO", nullable=true)
	private String testo;
	
	//Getters and setters
	public User getMittente() {
		return mittente;
	}

	public void setMittente(User mittente) {
		this.mittente = mittente;
	}

	public User getDestinatario() {
		return destinatario;
	}


	public void setDestinatario(User destinatario) {
		this.destinatario = destinatario;
	}

	public Skill getSkillRichiesta() {
		return skillRichiesta;
	}

	public void setSkillRichiesta(Skill skillRichiesta) {
		this.skillRichiesta = skillRichiesta;
	}

	public boolean isMessaggioLetto() {
		return isMessaggioLetto;
	}

	public void setMessaggioLetto(boolean isMessaggioLetto) {
		this.isMessaggioLetto = isMessaggioLetto;
	}

	public boolean isRichiestaAiuto() {
		return isRichiestaAiuto;
	}

	public void setRichiestaAiuto(boolean isRichiestaAiuto) {
		this.isRichiestaAiuto = isRichiestaAiuto;
	}

	public Date getDataInvio() {
		return dataInvio;
	}

	public void setDataInvio(Date dataInvio) {
		this.dataInvio = dataInvio;
	}

	public String getTesto() {
		return testo;
	}

	public void setTesto(String testo) {
		this.testo = testo;
	}

	public long getId() {
		return id;
	}
	
	public String toString() {
		if (this.isRichiestaAiuto==true) 
			return ("FROM: "+ this.getMittente().toString() +" TO "+ this.getDestinatario().toString() +". SKILL RICHIESTA: "+ this.getSkillRichiesta().toString() +" ");
		return ("FROM: "+ this.getMittente().toString() +" TO "+ this.getDestinatario().toString());

	}
}
