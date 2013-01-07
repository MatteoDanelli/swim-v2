package SE2.Swimv2.Servlet;

import java.io.IOException;
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
 * Servlet implementation class RicercaUtentiServlet
 */
public class RicercaUtentiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//nomi attributi
	private static final String NOME= "nome";
	private static final String COGNOME= "cognome";
	private static final String SKILL= "skill";
	private static final String RISULTATI_RICERCA= "RisultatiRicerca";
	private static final String MESSAGE = "Messaggio";
	
	//valori attributi
	private static final String NESSUN_RISULTATO = "La Ricerca non ha prodotto nessun risultato";
	
	//nomi paramentri
	private static final String TIPO_RICERCA= "type";

	//valori paramentri
	private static final String RICERCA_SKILL= "skill";
	private static final String RICERCA_NOMINATIVO= "nominativo"; 
	
	//nomi pagine
	private static final String ERROR_PAGE = "error.jsp";
    
	private RemoteManager remoteManager= new RemoteManager();
	private GestoreUserRemote gestoreUser;
	/**
     * @see HttpServlet#HttpServlet()
     */
    public RicercaUtentiServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/Guest/cerca_utenti.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String type= request.getParameter(TIPO_RICERCA);
		
		try {
			gestoreUser= remoteManager.getGestoreUserRemote();
		} catch (NamingException e) {
			response.sendRedirect(ERROR_PAGE);
			return;
		}
		
		if(type.equals(RICERCA_SKILL)){
			String skill = request.getParameter(SKILL);
			List<User> risultati = gestoreUser.cercaPerSkill(skill);
			request.setAttribute(RISULTATI_RICERCA, risultati);
			if(risultati.size()==0){
				request.setAttribute(MESSAGE, NESSUN_RISULTATO);
			}
		
		}
		else if(type.equals(RICERCA_NOMINATIVO)){
			String nome = request.getParameter(NOME);
			String cognome = request.getParameter(COGNOME);
			List<User> risultati = gestoreUser.cercaPerNominativo(nome, cognome);
			request.setAttribute(RISULTATI_RICERCA, risultati);
			if(risultati.size()==0){
				request.setAttribute(MESSAGE, NESSUN_RISULTATO);
			}
		}
		

		
		request.getRequestDispatcher("/Guest/cerca_utenti.jsp").forward(request, response);
	}

}
