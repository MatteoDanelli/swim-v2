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
 * @author Matteo Danelli
 * Tale classi modella i feedback ricevuti e assegnati dagli/agli user.
 */
@Entity
@Table(name="FEEDBACK")
public class Feedback implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID_FEEDBACK")
	private long id;
	
	@Column(name="STELLE", nullable=false)
	private int StelleAssegnate;
	
	@Column(name="COMMENTO", nullable=true,columnDefinition="TEXT")
	private String commento;
	
	@Column(name="DATA", nullable=false)
	private Calendar dataPubblicazione;
	
	@ManyToOne
	@JoinColumn(name="MITTENTE")
	private User mittente;
	
	@ManyToOne
	@JoinColumn(name="DESTINATARIO")
	private User destinatario;
	
	public Feedback(){
	}
	
	public Feedback(User mittente, User destinatario,int stelle, String commento) {
		this.mittente = mittente;
		this.destinatario= destinatario;
		this.StelleAssegnate=stelle;
		this.commento=commento;
		this.dataPubblicazione=new GregorianCalendar();
	}

	//Getters
	public long getId() {
		return id;
	}

	public int getStelleAssegnate() {
		return StelleAssegnate;
	}

	public String getCommento() {
		return commento;
	}

	public Calendar getDataPubblicazione() {
		return dataPubblicazione;
	}

	public User getMittente() {
		return mittente;
	}

	public User getDestinatario() {
		return destinatario;
	}
	
}
