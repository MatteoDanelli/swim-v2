/**
 * 
 */
package SE2.Swimv2.Entity;

import java.io.Serializable;

import javax.persistence.*;

/**
 * @author Matteo Danelli
 * Tale classa modella le richiesta di amicizia, mettendo in relazione un user ocn un altro user.
 */
@Entity
@Table(name="RICHIESTE_AMICIZIA")
public class RichiestaAmicizia implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID_RICHIESTA_SKILL")
	private long id;
	
	@ManyToOne
	@JoinColumn(name="MITTENTE", nullable=false)
	private User mittente;
	
	@ManyToOne
	@JoinColumn(name="DESTINATARIO", nullable=false)
	private User destinatario;

	public RichiestaAmicizia(){
	}
	
	public RichiestaAmicizia(User mittente, User destinatario){
		this.mittente = mittente;
		this.destinatario = destinatario;
	}
	
	//Getters
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
