package SE2.Swimv2.Entity;
import java.io.Serializable;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.persistence.*;

/**
 * @author Matteo Danelli
 * Questa classe modella l'entità relativa all'admin del sistema.
 * Ad essa farà riferimento una ttabella "admin" nel database.
 * La tabella avrà due colonne: un nome e una password, codificata in md5.
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
	
	/**
	 * Utilizza l'algoritmo md5 per codificare la password.
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = md5(password);
	}

	public long getId() {
		return id;
	}
	
	/**
	 * Calcola l'hash md5 di una stringa
	 * @param textToCode string adi cui fare l'hashing
	 * @return string hashed
	 */
	private String md5(String textToCode) {
        String md5 = null;        
        if(null == textToCode) return null;
         
        try {         
        //Create MessageDigest object for MD5
        MessageDigest digest = MessageDigest.getInstance("MD5");
        //Update input string in message digest
        digest.update(textToCode.getBytes(), 0, textToCode.length());
        //Converts message digest value in base 16 (hex)
        md5 = new BigInteger(1, digest.digest()).toString(16);
 
        } 
        catch (NoSuchAlgorithmException e) {
             e.printStackTrace();
        }
        return md5;
    }
	
}

