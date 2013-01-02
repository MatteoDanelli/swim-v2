package SE2.Swimv2.Session;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;


import SE2.Swimv2.Entity.Skill;
import SE2.Swimv2.Entity.User;
import SE2.Swimv2.Exceptions.UserException;

/**
 * @author Daniel Cantoni, Matteo Danelli
 * Session Bean che implementa l'interfaccia GestoreUserRemote.
 */
@Stateless
public class GestoreUser implements GestoreUserRemote {
	@PersistenceContext(unitName = "Swimv2")
	EntityManager database;
	@EJB 
	private GestoreAmiciLocal gestoreAmici;

    /**
     *  Inserisce un nuovo utente,  assegnandoli tutti i suoi attributi.
     */
	@Override
	public long addUser(String email, String password, String nome,
			String cognome, String provincia, char sesso, Date dataNascita,
			Set<Skill> personalSkill) throws UserException {

		User newUser = new User(email, password, nome, cognome, provincia, sesso, dataNascita, personalSkill);
    	try{
    		database.persist(newUser);
    		database.flush();
    		return newUser.getId();
    	}catch(PersistenceException e){
    		throw new UserException("Errore, utente non inserito");
    	}    	
	}

	@Override
	public void modificaEmail(long userId, String email) throws UserException {
		
		User user = database.find(User.class, userId);
		
		try{
			user.setEmail(email);
			database.flush();
		}catch(NullPointerException e){
 		}catch(PersistenceException e){
    	}catch(IllegalStateException e){
    	}
		throw new UserException("Errore, dati non modificati");
		
	}

	@Override
	public void modificaPassword(long userId, String password)throws UserException {
		
		User user = database.find(User.class, userId);
		try{
			user.setPassword(password);
			database.flush();
		}catch(NullPointerException e){
 		}catch(PersistenceException e){
    	}catch(IllegalStateException e){
    	}
		throw new UserException("Errore, dati non modificati");
		
	}

	@Override
	public void modificaAnagrafica(long userId, String nome, String cognome,
			String provincia, char sesso, Date dataNascita)
			throws UserException {
		
		User user = database.find(User.class, userId);
		try{
			user.setNome(nome);
			user.setCognome(cognome);
			user.setProvincia(provincia);
			user.setSesso(sesso);
			user.setDataDiNascita(dataNascita);
			database.flush();
		}catch(NullPointerException e){
 		}catch(PersistenceException e){
    	}catch(IllegalStateException e){
    	}
		throw new UserException("Errore, dati non modificati");
		
	}

	@Override
	public void modificaPersonalSkill(long userId, Set<Skill> personalSkill)
			throws UserException {
		
		User user = database.find(User.class, userId);
		try{
			user.setSkillPossedute(personalSkill);
			database.flush();
		}catch(NullPointerException e){
 		}catch(PersistenceException e){
    	}catch(IllegalStateException e){
    	}
		throw new UserException("Errore, dati non modificati");
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> cercaPerNome(String nome) {

		Query q = database.createQuery("FROM User u WHERE u.nome = :nome ORDER BY u.nome asc, u.cognome asc");
		q.setParameter("nome", nome);
		
		List<User> result =(List<User>) q.getResultList();
		return result;
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> cercaPerCognome(String cognome) {

		Query q = database.createQuery("FROM User u WHERE u.cognome = :cognome ORDER BY u.cognome asc, u.nome asc");
		q.setParameter("cognome", cognome);
		
		List<User> result =(List<User>) q.getResultList();
		return result;
	
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> cercaPerNominativo(String nome, String cognome) {
		
		Query q = database.createQuery("FROM User u WHERE u.cognome = :cognome and u.nome = :nome ORDER BY u.cognome asc, u.nome asc");
		q.setParameter("cognome", cognome);
		q.setParameter("nome", nome);
		
		List<User> result =(List<User>) q.getResultList();
		return result;
	
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> cercaPerSkill(String nome){
		
		Query q = database.createQuery("SELECT u FROM Skill s, User u "+
										"WHERE s.nome=:nome and u in elements(s.UserCheLaPossiedono) "+
										"ORDER BY u.cognome asc,u.nome asc ");
		q.setParameter("nome", nome);
		
		List<User> result =(List<User>) q.getResultList();
		return result;

	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<User> cercaAmiciPerNome(long userId, String nome) {
		
		Set<User> amici = gestoreAmici.elencoAmici(userId);
		
		//se non ho amici è innutile che lancio la query
		if(amici==null || amici.size()==0){
			List<User> emptyList = new LinkedList<User>();
			return emptyList;
		}
		
		Query q = database.createQuery("FROM User u WHERE u.nome = :nome and u in (:amici) ORDER BY u.nome asc, u.cognome asc");
		q.setParameter("nome", nome);
		q.setParameter("amici", amici);
		
		List<User> result =(List<User>) q.getResultList();
		return result;
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> cercaAmiciPerCognome(long userId, String cognome) {
		
		Set<User> amici = gestoreAmici.elencoAmici(userId);
	
		//se non ho amici è innutile che lancio la query
		if(amici==null || amici.size()==0){
			List<User> emptyList = new LinkedList<User>();
			return emptyList;
		}
		
		Query q = database.createQuery("FROM User u WHERE u.cognome = :cognome and u in (:amici) ORDER BY u.cognome asc,u.nome asc");
		q.setParameter("cognome", cognome);
		q.setParameter("amici", amici);
		
		List<User> result =(List<User>) q.getResultList();
		return result;
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> cercaAmiciPerNominativo(long userId, String nome,
			String cognome) {
		
		Set<User> amici = gestoreAmici.elencoAmici(userId);
		
		//se non ho amici è innutile che lancio la query
		if(amici==null || amici.size()==0){
			List<User> emptyList = new LinkedList<User>();
			return emptyList;
		}
		
		Query q = database.createQuery("FROM User u WHERE u.cognome = :cognome and u.nome = :nome " +
									   "and u in (:amici) ORDER BY u.cognome asc,u.nome asc");
		q.setParameter("nome", nome);
		q.setParameter("cognome", cognome);
		q.setParameter("amici", amici);
		
		List<User> result =(List<User>) q.getResultList();
		return result;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> cercaAmiciPerSkill(long userId, String nome) {

		Set<User> amici = gestoreAmici.elencoAmici(userId);
		
		//se non ho amici è innutile che lancio la query
		if(amici==null || amici.size()==0){
			List<User> emptyList = new LinkedList<User>();
			return emptyList;
		}
				
		Query q = database.createQuery("SELECT u FROM Skill s, User u "+
										"WHERE s.nome=:nome and u in elements(s.UserCheLaPossiedono) "+
										"and u in (:amici) "+
										"ORDER BY u.cognome asc,u.nome asc ");
		q.setParameter("nome", nome);
		q.setParameter("amici", amici);
		
		List<User> result =(List<User>) q.getResultList();
		return result;

	}

	@Override
	public User getById(long userId) {
		User result= database.find(User.class, userId);
		return result;
	}

	@Override
	public User getByEmail(String email) {
		
		Query q = database.createQuery("FROM User u WHERE u.email= :email ");
		q.setParameter("email", email);
		
		try{
			User result =(User) q.getSingleResult();
			return result;
		}catch (EntityNotFoundException e){
		}catch (NoResultException e) {
		}catch (NonUniqueResultException e){
		}
		return null;		
	}
	
}
