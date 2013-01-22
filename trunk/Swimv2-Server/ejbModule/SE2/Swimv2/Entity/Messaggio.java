/**
 * 
 */
package SE2.Swimv2.Entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Matteo Danelli Tale classe rappresenta i messaggi all'interno del
 *         sistema, siano essi richieste di aiuto che conversazioni tra user.
 */
@Entity
@Table(name = "MESSAGGIO")
public class Messaggio implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_MESSAGGIO")
	private long id;

	@ManyToOne
	@JoinColumn(name = "MITTENTE", nullable = false)
	private User mittente;

	@ManyToOne
	@JoinColumn(name = "DESTINATARIO", nullable = false)
	private User destinatario;

	@ManyToOne
	@JoinColumn(name = "SKILL_RICHIESTA", nullable = true)
	private Skill skillRichiesta;

	@Column(name = "MESSAGGIO_LETTO", nullable = true)
	private boolean isMessaggioLetto;

	@Column(name = "RICHIESTA_DI_AIUTO", nullable = true)
	private boolean isRichiestaAiuto;

	@Column(name = "DATA_DI_INVIO", nullable = false)
	private Calendar dataInvio;

	@Column(name = "TESTO", nullable = true, columnDefinition="TEXT")
	private String testo;

	// costruttori

	public Messaggio() {

	}
	
	public Messaggio(User mittente, User destinatario, Skill skillRichiesta, boolean isRichiestaAiuto, String testo) {
		
		this.mittente = mittente;
		this.destinatario = destinatario;
		this.skillRichiesta = skillRichiesta;
		this.isRichiestaAiuto = isRichiestaAiuto;
		this.dataInvio = new GregorianCalendar();
		this.testo = testo;
		this.isMessaggioLetto=false;
		
	}
	
	// Getters

	public long getId() {
		return id;
	}

	public User getMittente() {
		return mittente;
	}

	public User getDestinatario() {
		return destinatario;
	}

	public Skill getSkillRichiesta() {
		return skillRichiesta;
	}

	public boolean isMessaggioLetto() {
		return isMessaggioLetto;
	}

	public boolean isRichiestaAiuto() {
		return isRichiestaAiuto;
	}

	public Calendar getDataInvio() {
		return dataInvio;
	}

	public String getTesto() {
		return testo;
	}
	
	//setters
	
	public void setMessaggioLetto(boolean isMessaggioLetto) {
		this.isMessaggioLetto = isMessaggioLetto;
	}

	public String toString() {
		if (this.isRichiestaAiuto == true)
			return ("FROM: " + this.getMittente().toString() + " TO "
					+ this.getDestinatario().toString() + ". SKILL RICHIESTA: "
					+ this.getSkillRichiesta().toString() + " ");
		return ("FROM: " + this.getMittente().toString() + " TO " + this
				.getDestinatario().toString());

	}

	@Override
	public boolean equals(Object obj) {
		
		if (obj instanceof Messaggio) {
			Messaggio other= (Messaggio)obj;
			if(this.getId()==other.getId()){
				return true;
			}
		}
		return false;
	}

	@Override
	public int hashCode() {
		String idStr= String.valueOf(this.getId());
		return idStr.hashCode();
	}

}
