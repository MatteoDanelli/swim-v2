package SE2.Swimv2.Servlet;

import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import SE2.Swimv2.Entity.User;
import SE2.Swimv2.Exceptions.UserException;
import SE2.Swimv2.Session.GestoreUserRemote;
import SE2.Swimv2.Util.RemoteManager;

/**
 * Servlet implementation class ModificaDatiServlet
 */
public class ModificaDatiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//nomi parametri
	private static final String ERROR = "Errore";
	private static final String USER= "user";
	private static final String EMAIL="email";
	private static final String PASSWORD="password";
	private static final String NOME="nome";
	private static final String COGNOME="cognome";
	private static final String PROVINCIA="provincia";
	private static final String SESSO="sesso";
	private static final String GIORNO_NASCITA="giornoNascita";
	private static final String MESE_NASCITA="meseNascita";
	private static final String ANNO_NASCITA="annoNascita";
	
	//valori attributi
	private static final String LOGIN_ERROR= "logError";
	private static final String USER_ID= "userId";
	
	//nomi pagine
	private static final String HOME_PAGE = "index.jsp";
	private static final String USER_MODIFICA_DATI_PAGE = "User/modifica_dati.jsp";
	private static final String ERROR_PAGE = "error.jsp";
	
	//servlet
	private static final String USER_SERVLET = "/Swimv2-Client/UserServlet";
    
	private RemoteManager remoteManager= new RemoteManager();
	private GestoreUserRemote gestoreUser;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModificaDatiServlet() {
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
		
		try {
			gestoreUser = remoteManager.getGestoreUserRemote();
			User user= gestoreUser.getById(id.longValue());
			request.setAttribute(USER, user);
		} catch (NamingException e) {
			response.sendRedirect(ERROR_PAGE);
			return;
		}
		
		request.getRequestDispatcher(USER_MODIFICA_DATI_PAGE).forward(request, response);

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
				
		String email= request.getParameter(EMAIL);
		String psw= request.getParameter(PASSWORD);
		String nome= request.getParameter(NOME);
		String cognome= request.getParameter(COGNOME);
		String provincia= request.getParameter(PROVINCIA);
		String sessoString= request.getParameter(SESSO);
		char sesso = sessoString.charAt(0);
		String giornoNascita= request.getParameter(GIORNO_NASCITA);
		String meseNascita= request.getParameter(MESE_NASCITA);
		String annoNascita= request.getParameter(ANNO_NASCITA);
		
		Calendar data = null;
		
		//verifico che i parametri della data siano corretti
		if(giornoNascita!="" && meseNascita!="" && annoNascita!=""){
			int giorno = Integer.parseInt(giornoNascita);
			int mese = Integer.parseInt(meseNascita);
			int anno = Integer.parseInt(annoNascita);
			data= new GregorianCalendar(anno,mese,giorno);
		}
		//se provincia non inserita, setto il parametro a null
		if(provincia==""){
			provincia=null;
		}
		
		try {
			gestoreUser = remoteManager.getGestoreUserRemote();
		} catch (NamingException e) {
			response.sendRedirect(ERROR_PAGE);
			return;
		}
		
		try {
			gestoreUser.modificaAnagrafica(id, nome, cognome, provincia, sesso, data);
		} catch (UserException e) {
			request.setAttribute(ERROR, "Dati Non Validi");
			request.getRequestDispatcher(ERROR_PAGE).forward(request, response);
			return;
		}
		
		try {
			gestoreUser.modificaEmail(id, email);
		} catch (UserException e) {
			request.setAttribute(ERROR, "Email Non Valida");
			request.getRequestDispatcher(ERROR_PAGE).forward(request, response);
			return;
		}
		
		try {
			if(psw!=""){
				gestoreUser.modificaPassword(id, psw);
			}
		}catch (UserException e){
			request.setAttribute(ERROR, "Password Non Valida");
			request.getRequestDispatcher(ERROR_PAGE).forward(request, response);
			return;
		}
		
		response.sendRedirect(USER_SERVLET);

	}

}
