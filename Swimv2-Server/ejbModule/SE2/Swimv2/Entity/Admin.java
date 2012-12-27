package SE2.Swimv2.Entity;
import java.io.Serializable;

import javax.persistence.*;

/**
 * @author Matteo Danelli
 * Questa classe modella l'entità relativa all'admin del sistema.
 * Ad essa farà riferimento una ttabella "admin" nel database.
 * La tabella avrà due colonne: un nome e una password, codificata in md5.
 * 
 */

@Entity
@Table(name="ADMIN")
public class Admin implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID_ADMIN")
	private long id;
	@Column(name="EMAIL",unique=true,nullable=false)
	private String email;
	@Column(name="PASSWORD",unique=true,nullable=false)
	private String password;
	
	
	//Default constructor
	public Admin () {		
	}
	
	public Admin(String email, String password) {
		this.email=email;
		this.password=password;
	}
	
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

	public long getId() {
		return id;
	}
	
}

