package SE2.Swimv2.Session;

import java.util.Calendar;
import java.util.List;
import java.util.Set;

import javax.ejb.Remote;

import SE2.Swimv2.Exceptions.UserException;

import SE2.Swimv2.Entity.Skill;
import SE2.Swimv2.Entity.User;
/**
 * @author Daniel Cantoni
 * Interfaccia per la gestione degli user
 */
@Remote
public interface GestoreUserRemote {

	public long addUser(String email, String password, String nome, String cognome, String provincia, char sesso, Calendar dataNascita, Set<Skill> personalSkill) throws UserException;
	public void modificaEmail(long userId, String email)throws UserException;
	public void modificaPassword(long userId, String password)throws UserException;
	public void modificaAnagrafica(long userId, String nome, String cognome, String provincia, char sesso, Calendar datanascita)throws UserException;
	public void modificaPersonalSkill(long userId, Set<Skill> personalSkill)throws UserException;
	public List<User> cercaPerNome(String nome);
	public List<User> cercaPerCognome(String cognome);
	public List<User> cercaPerNomeCognome(String nome,String cognome);
	public List<User> cercaPerNominativo(String nome,String cognome);
	public List<User> cercaPerSkill(String nome);
	public List<User> cercaAmiciPerNome(long userId,String nome);
	public List<User> cercaAmiciPerCognome(long userId, String cognome);
	public List<User> cercaAmiciPerNomeCognome(long userId,String nome,String cognome);
	public List<User> cercaAmiciPerNominativo(long userId, String nome,String cognome);
	public List<User> cercaAmiciPerSkill(long userId, String nome);
	public User getById(long userId);
	public User getByEmail(String email);
}
