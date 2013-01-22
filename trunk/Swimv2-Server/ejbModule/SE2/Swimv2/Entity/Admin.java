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
	@Column(name="USERNAME",unique=true,nullable=false)
	private String username;
	@Column(name="PASSWORD",unique=true,nullable=false)
	private String password;
	
	
	//Default constructor
	public Admin () {		
	}
	
	public Admin(String username, String password) {
		this.username=username;
		this.password=password;
	}
	
	//Getters
	public String getEmail() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public long getId() {
		return id;
	}
	
	//Setters
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if (obj instanceof Admin) {
			Admin other= (Admin)obj;
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

