package SE2.Swimv2.Session;

import java.util.Calendar;
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
 * @author Daniel Cantoni, Matteo Danelli Session Bean che implementa
 *         l'interfaccia GestoreUserRemote.
 */
@Stateless
public class GestoreUser implements GestoreUserRemote {
	@PersistenceContext(unitName = "Swimv2")
	EntityManager database;
	@EJB
	private GestoreAmiciLocal gestoreAmici;

	/**
	 * Inserisce un nuovo utente, assegnandoli tutti i suoi attributi.
	 */
	@Override
	public long addUser(String email, String password, String nome,
			String cognome, String provincia, char sesso, Calendar dataNascita,
			Set<Skill> personalSkill) throws UserException {

		if (!this.verificaCampiAnagrafica(nome, cognome, provincia, sesso,
				dataNascita)) {
			throw new UserException("Errore, user non inserito");
		}

		if (!this.verificaCampiCredenziali(email, password)) {
			throw new UserException("Errore, user non inserito");
		}

		User newUser = new User(email, password, nome, cognome, provincia,
				sesso, dataNascita, personalSkill);
		try {
			database.persist(newUser);
			database.flush();
			return newUser.getId();
		} catch (PersistenceException e) {
			throw new UserException("Errore, utente non inserito");
		}
	}

	/**
	 * Modifica il campo e mail di un utente
	 */
	@Override
	public void modificaEmail(long userId, String email) throws UserException {

		if (!this.verificaCorrettezzaEmail(email)) {
			throw new UserException("Errore, dati non modificat");
		}

		User user = database.find(User.class, userId);

		try {
			user.setEmail(email);
			database.flush();
			return;
		} catch (NullPointerException e) {
		} catch (PersistenceException e) {
		} catch (IllegalStateException e) {
		}
		throw new UserException("Errore, dati non modificati");

	}
	
	/**
	 * Modifica la password di un utente
	 */
	@Override
	public void modificaPassword(long userId, String password)
			throws UserException {

		if (!this.verificaCorrettezzapassword(password)) {
			throw new UserException("Errore, dati non modificati");
		}

		User user = database.find(User.class, userId);
		try {
			user.setPassword(password);
			database.flush();
			return;
		} catch (NullPointerException e) {
		} catch (PersistenceException e) {
		} catch (IllegalStateException e) {
		}
		throw new UserException("Errore, dati non modificati");

	}

	/**
	 * Modifica i dati anagrafici di un user
	 */
	@Override
	public void modificaAnagrafica(long userId, String nome, String cognome,
			String provincia, char sesso, Calendar dataNascita)
			throws UserException {

		if (!this.verificaCampiAnagrafica(nome, cognome, provincia, sesso,
				dataNascita)) {
			throw new UserException("Errore, dati non modificati");
		}

		User user = database.find(User.class, userId);
		try {
			user.setNome(nome);
			user.setCognome(cognome);
			user.setProvincia(provincia);
			user.setSesso(sesso);
			user.setDataDiNascita(dataNascita);
			database.flush();
			return;
		} catch (NullPointerException e) {
		} catch (PersistenceException e) {
		} catch (IllegalStateException e) {
		}
		throw new UserException("Errore, dati non modificati");

	}

	/**
	 * Modifica il personal skill di un user
	 */
	@Override
	public void modificaPersonalSkill(long userId, Set<Skill> personalSkill)
			throws UserException {

		User user = database.find(User.class, userId);
		try {
			user.setSkillPossedute(personalSkill);
			database.flush();
			return;
		} catch (NullPointerException e) {
		} catch (PersistenceException e) {
		} catch (IllegalStateException e) {
		}
		throw new UserException("Errore, dati non modificati");

	}

	/**
	 * Cerca utente per nome
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<User> cercaPerNome(String nome) {

		Query q = database
				.createQuery("FROM User u WHERE u.nome = :nome ORDER BY u.nome asc, u.cognome asc");
		q.setParameter("nome", nome);

		List<User> result = (List<User>) q.getResultList();
		return result;

	}

	/**
	 * Creca un user per cognome
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<User> cercaPerCognome(String cognome) {

		Query q = database
				.createQuery("FROM User u WHERE u.cognome = :cognome ORDER BY u.cognome asc, u.nome asc");
		q.setParameter("cognome", cognome);

		List<User> result = (List<User>) q.getResultList();
		return result;

	}

	/**
	 * cerca un utente per nome+cognome, sono richiesti entrambi i campi
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<User> cercaPerNomeCognome(String nome, String cognome) {

		Query q = database
				.createQuery("FROM User u WHERE u.cognome = :cognome and u.nome = :nome ORDER BY u.cognome asc, u.nome asc");
		q.setParameter("cognome", cognome);
		q.setParameter("nome", nome);

		List<User> result = (List<User>) q.getResultList();
		return result;

	}
	/**
	 * Cerca un utente per Nominativo, cioè nome e/o cognome
	 */
	@Override
	public List<User> cercaPerNominativo(String nome, String cognome) {

		if(!nome.equals("")&&cognome.equals("")){
			return this.cercaPerNome(nome);
		}else if(nome.equals("")&&!cognome.equals("")){
			return this.cercaPerCognome(cognome);
		}else{
			return this.cercaPerNomeCognome(nome, cognome);
		}

	}

	/**
	 * Cerca utenti per skill
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<User> cercaPerSkill(String nome) {

		Query q = database
				.createQuery("SELECT u FROM Skill s, User u "
						+ "WHERE s.nome=:nome and u in elements(s.UserCheLaPossiedono) "
						+ "ORDER BY u.cognome asc,u.nome asc ");
		q.setParameter("nome", nome);

		List<User> result = (List<User>) q.getResultList();
		return result;

	}

	/**
	 * Cerca un user amico per nome
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<User> cercaAmiciPerNome(long userId, String nome) {

		Set<User> amici = gestoreAmici.elencoAmici(userId);

		// se non ho amici è innutile che lancio la query
		if (amici == null || amici.size() == 0) {
			List<User> emptyList = new LinkedList<User>();
			return emptyList;
		}

		Query q = database
				.createQuery("FROM User u WHERE u.nome = :nome and u in (:amici) ORDER BY u.nome asc, u.cognome asc");
		q.setParameter("nome", nome);
		q.setParameter("amici", amici);

		List<User> result = (List<User>) q.getResultList();
		return result;

	}
	/**
	 * Cerca un user amico per cognome
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<User> cercaAmiciPerCognome(long userId, String cognome) {

		Set<User> amici = gestoreAmici.elencoAmici(userId);

		// se non ho amici è innutile che lancio la query
		if (amici == null || amici.size() == 0) {
			List<User> emptyList = new LinkedList<User>();
			return emptyList;
		}

		Query q = database
				.createQuery("FROM User u WHERE u.cognome = :cognome and u in (:amici) ORDER BY u.cognome asc,u.nome asc");
		q.setParameter("cognome", cognome);
		q.setParameter("amici", amici);

		List<User> result = (List<User>) q.getResultList();
		return result;

	}

	/**
	 * cerca un utente amico per nome+cognome, sono richiesti entrambi i campi
	 */	
	@SuppressWarnings("unchecked")
	@Override
	public List<User> cercaAmiciPerNomeCognome(long userId, String nome,
			String cognome) {

		Set<User> amici = gestoreAmici.elencoAmici(userId);

		// se non ho amici è innutile che lancio la query
		if (amici == null || amici.size() == 0) {
			List<User> emptyList = new LinkedList<User>();
			return emptyList;
		}

		Query q = database
				.createQuery("FROM User u WHERE u.cognome = :cognome and u.nome = :nome "
						+ "and u in (:amici) ORDER BY u.cognome asc,u.nome asc");
		q.setParameter("nome", nome);
		q.setParameter("cognome", cognome);
		q.setParameter("amici", amici);

		List<User> result = (List<User>) q.getResultList();
		return result;

	}
	/**
	 * cerca un utente amico per nome+cognome, sono richiesti entrambi i campi
	 */	
	@Override
	public List<User> cercaAmiciPerNominativo(long userId, String nome,String cognome) {

		if(!nome.equals("")&&cognome.equals("")){
			return this.cercaAmiciPerNome(userId,nome);
		}else if(nome.equals("")&&!cognome.equals("")){
			return this.cercaAmiciPerCognome(userId,cognome);
		}else{
			return this.cercaAmiciPerNomeCognome(userId,nome, cognome);
		}

	}
	
	/**
	 * cerca un user amico per skill
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<User> cercaAmiciPerSkill(long userId, String nome) {

		Set<User> amici = gestoreAmici.elencoAmici(userId);

		// se non ho amici è innutile che lancio la query
		if (amici == null || amici.size() == 0) {
			List<User> emptyList = new LinkedList<User>();
			return emptyList;
		}

		Query q = database
				.createQuery("SELECT u FROM Skill s, User u "
						+ "WHERE s.nome=:nome and u in elements(s.UserCheLaPossiedono) "
						+ "and u in (:amici) "
						+ "ORDER BY u.cognome asc,u.nome asc ");
		q.setParameter("nome", nome);
		q.setParameter("amici", amici);

		List<User> result = (List<User>) q.getResultList();
		return result;

	}

	/**
	 * Dato l'id dell'utente, ritorna user
	 */
	@Override
	public User getById(long userId) {
		User result = database.find(User.class, userId);
		return result;
	}

	/**
	 * Dato l'email dell'utente, ritorna user
	 */
	@Override
	public User getByEmail(String email) {

		Query q = database.createQuery("FROM User u WHERE u.email= :email ");
		q.setParameter("email", email);

		try {
			User result = (User) q.getSingleResult();
			return result;
		} catch (EntityNotFoundException e) {
		} catch (NoResultException e) {
		} catch (NonUniqueResultException e) {
		}
		return null;
	}

	private boolean verificaCampiAnagrafica(String nome, String cognome,String provincia, char sesso, Calendar dataNascita) {
		if (nome == null || nome.equals("")) {
			return false;
		}
		if (cognome == null || cognome.equals("")) {
			return false;
		}
		if (provincia != null && provincia.equals("")) {
			return false;
		}
		if (sesso == ' ') {
			return false;
		}

		return true;
	}

	private boolean verificaCampiCredenziali(String email, String password) {
		if (!verificaCorrettezzaEmail(email)) {
			return false;
		}
		if (!verificaCorrettezzapassword(password)) {
			return false;
		}

		return true;
	}

	private boolean verificaCorrettezzaEmail(String email) {
		if (email == null || email.equals("")) {
			return false;
		}
		return true;
	}

	private boolean verificaCorrettezzapassword(String password) {
		if (password == null || password.equals("")) {
			return false;
		}
		return true;
	}
}
