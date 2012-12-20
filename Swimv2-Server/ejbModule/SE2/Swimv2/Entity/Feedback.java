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
 * Tale classi modella i feddback ricevuti e assegnati dagli/agli user.
 */
@Entity
@Table(name="FEEDBACK")
public class Feedback implements Serializable {
	
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
	@Column(name="DESTINATARIO")
	private User destinatario;

	/**
	 * @return the stelleAssegnate
	 */
	public int getStelleAssegnate() {
		return StelleAssegnate;
	}

	/**
	 * @param stelleAssegnate the stelleAssegnate to set
	 */
	public void setStelleAssegnate(int stelleAssegnate) {
		StelleAssegnate = stelleAssegnate;
	}

	/**
	 * @return the commento
	 */
	public String getCommento() {
		return commento;
	}

	/**
	 * @param commento the commento to set
	 */
	public void setCommento(String commento) {
		this.commento = commento;
	}

	/**
	 * @return the dataPubblicazione
	 */
	public Date getDataPubblicazione() {
		return dataPubblicazione;
	}

	/**
	 * @param dataPubblicazione the dataPubblicazione to set
	 */
	public void setDataPubblicazione(Date dataPubblicazione) {
		this.dataPubblicazione = dataPubblicazione;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @return the mittente
	 */
	public User getMittente() {
		return mittente;
	}

	/**
	 * @return the destinatario
	 */
	public User getDestinatario() {
		return destinatario;
	}

}
