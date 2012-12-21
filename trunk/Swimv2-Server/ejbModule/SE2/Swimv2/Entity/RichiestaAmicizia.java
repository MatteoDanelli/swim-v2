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

	//Getters and setters

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
