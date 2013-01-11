package SE2.Swimv2.Servlet;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import SE2.Swimv2.Entity.User;
import SE2.Swimv2.Session.GestoreUserRemote;
import SE2.Swimv2.Util.RemoteManager;

/**
 * Servlet implementation class RicercaUtentiUserServlet
 */
public class RicercaUtentiUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//nomi attributi
	private static final String ERROR = "Errore";
	private static final String NOME= "nome";
	private static final String COGNOME= "cognome";
	private static final String SKILL= "skill";
	private static final String RISULTATI_RICERCA= "RisultatiRicerca";
	private static final String MESSAGE = "Messaggio";
	
	//valori attributi
	private static final String LOGIN_ERROR= "logError";
	private static final String USER_ID= "userId";
	private static final String NESSUN_RISULTATO = "La Ricerca non ha prodotto nessun risultato";
	
	//nomi paramentri
	private static final String TIPO_RICERCA= "type";
	private static final String SOLO_AMICI="ricercaAmici";

	//valori paramentri
	private static final String RICERCA_SKILL= "skill";
	private static final String RICERCA_NOMINATIVO= "nominativo"; 
	
	//nomi pagine
	private static final String ERROR_PAGE = "error.jsp";
	private static final String HOME_PAGE = "index.jsp";
	private static final String USER_CERCA_UTENTI = "User/cercaUtenti.jsp";
    
	private RemoteManager remoteManager= new RemoteManager();
	private GestoreUserRemote gestoreUser;
	/**
     * @see HttpServlet#HttpServlet()
     */
    public RicercaUtentiUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//se non esiste una sessione richiamo l' home page
		Long id= (Long) request.getSession().getAttribute(USER_ID);
		if(id==null){
			request.setAttribute(ERROR, LOGIN_ERROR);
			request.getRequestDispatcher(HOME_PAGE).forward(request, response);
			return;
		}
		
		request.getRequestDispatcher(USER_CERCA_UTENTI).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//se non esiste una sessione richiamo l' home page
		Long id= (Long) request.getSession().getAttribute(USER_ID);
		if(id==null){
			request.setAttribute(ERROR, LOGIN_ERROR);
			request.getRequestDispatcher(HOME_PAGE).forward(request, response);
			return;
		}
		
		String type= request.getParameter(TIPO_RICERCA);
		String amici= request.getParameter(SOLO_AMICI);
		
		try {
			gestoreUser= remoteManager.getGestoreUserRemote();
		} catch (NamingException e) {
			response.sendRedirect(ERROR_PAGE);
			return;
		}
		
		List<User> risultati;
		
		if(type.equals(RICERCA_SKILL)){
			String skill = request.getParameter(SKILL);
			risultati = cercaUserSkill(id,skill,amici,gestoreUser);
		}
		else if(type.equals(RICERCA_NOMINATIVO)){
			String nome = request.getParameter(NOME);
			String cognome = request.getParameter(COGNOME);
			risultati = cercaUserNominativo(id,nome,cognome,amici,gestoreUser);
		}else{
			response.sendRedirect(ERROR_PAGE);
			return;
		}
		
		request.setAttribute(RISULTATI_RICERCA, risultati);
		if(risultati.size()==0){
			request.setAttribute(MESSAGE, NESSUN_RISULTATO);
		}

		request.getRequestDispatcher(USER_CERCA_UTENTI).forward(request, response);
	}

	//metodo privato per la ricerca di un utente data la skill
	private List<User> cercaUserSkill(long id,String skill,String amici,GestoreUserRemote gestoreUser){
		
		List<User> risultati= new LinkedList<User>();
		if(skill==null){
			return risultati;
		}
		
		if(amici!=null){
			risultati=gestoreUser.cercaAmiciPerSkill(id,skill);
		}else{
			risultati = gestoreUser.cercaPerSkill(skill);
		}
		return risultati;
	}
	
	//metodo privato per la ricerca di un utente data il nominativo
	private List<User> cercaUserNominativo(long id,String nome,String cognome,String amici,GestoreUserRemote gestoreUser){
		
		List<User> risultati= new LinkedList<User>();
		if(nome==null || cognome==null){
			return risultati;
		}
		
		if(amici!=null){
			risultati=gestoreUser.cercaAmiciPerNominativo(id,nome, cognome);
		}else{
			risultati = gestoreUser.cercaPerNominativo(nome, cognome);
		}
		return risultati;
	}
}
