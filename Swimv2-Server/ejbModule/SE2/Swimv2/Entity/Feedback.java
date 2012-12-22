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
 * Tale classi modella i feedback ricevuti e assegnati dagli/agli user.
 */
@Entity
@Table(name="FEEDBACK")
public class Feedback implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public static final String DATE_FORMAT = "dd-MM-yyyy";
	
	public Feedback(int stelle, String commento) {
		this.StelleAssegnate=stelle;
		this.commento=commento;
		this.dataPubblicazione=new Date();
	}
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID_FEEDBACK")
	private long id;
	
	@Column(name="STELLE", nullable=false)
	private int StelleAssegnate;
	
	@Column(name="COMMENTO", nullable=true)
	private String commento;
	
	@Column(name="DATA", nullable=false)
	private Date dataPubblicazione;
	
	@ManyToOne
	@JoinColumn(name="MITTENTE")
	private User mittente;
	
	@ManyToOne
	@JoinColumn(name="DESTINATARIO")
	private User destinatario;

	//Getters and setters

	public int getStelleAssegnate() {
		return StelleAssegnate;
	}

	public void setStelleAssegnate(int stelleAssegnate) {
		StelleAssegnate = stelleAssegnate;
	}

	public String getCommento() {
		return commento;
	}

	public void setCommento(String commento) {
		this.commento = commento;
	}

	public Date getDataPubblicazione() {
		return dataPubblicazione;
	}

	public void setDataPubblicazione(Date dataPubblicazione) {
		this.dataPubblicazione=dataPubblicazione;
	}

	public long getId() {
		return id;
	}

	public User getMittente() {
		return mittente;
	}

	public User getDestinatario() {
		return destinatario;
	}
}
