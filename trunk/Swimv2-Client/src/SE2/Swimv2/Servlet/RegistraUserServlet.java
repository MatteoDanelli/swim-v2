package SE2.Swimv2.Servlet;

import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import SE2.Swimv2.Exceptions.LoginException;
import SE2.Swimv2.Exceptions.UserException;
import SE2.Swimv2.Session.GestoreLoginRemote;
import SE2.Swimv2.Session.GestoreUserRemote;
import SE2.Swimv2.Util.RemoteManager;

/**
 * Servlet implementation class RegistraUserServlet
 */
public class RegistraUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//nomi parametri
	private static final String ERROR = "Errore";
	private static final String EMAIL="email";
	private static final String PASSWORD="password";
	private static final String NOME="nome";
	private static final String COGNOME="cognome";
	private static final String PROVINCIA="provincia";
	private static final String SESSO="sesso";
	private static final String GIORNO_NASCITA="giornoNascita";
	private static final String MESE_NASCITA="meseNascita";
	private static final String ANNO_NASCITA="annoNascita";
	
	private static final String USER_ID= "userId";
	private static final String USER_SERVLET = "UserServlet";


	private static final String ERROR_PAGE = "error.jsp";
	private static final String REGISTRAZIONE = "/Guest/registrazione.jsp";	
	private static final String LOGIN_ERROR= "logError";

	
	private RemoteManager remoteManager= new RemoteManager();
	private GestoreUserRemote gestoreUser;
	private GestoreLoginRemote gestoreLogin;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistraUserServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			request.getRequestDispatcher(REGISTRAZIONE).forward(request, response);	
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		long idNuovoUser;

		String email= request.getParameter(EMAIL);
		String password= request.getParameter(PASSWORD);
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
			gestoreLogin = remoteManager.getGestoreLoginRemote();
		} catch (NamingException e) {
			response.sendRedirect(ERROR_PAGE);
			return;
		}
		
			try {
				gestoreUser.addUser(email, password, nome, cognome, provincia, sesso, data, null);
			} catch (UserException e) {
				request.setAttribute(ERROR, "Errore");
				request.getRequestDispatcher(ERROR_PAGE).forward(request,response);
				return;
			}

			try {
				idNuovoUser=gestoreLogin.loginUser(email, password);
				request.getSession().setAttribute(USER_ID, idNuovoUser);
				response.sendRedirect(USER_SERVLET);
			}
			catch (LoginException e) {
				request.setAttribute(ERROR, LOGIN_ERROR);
				request.getRequestDispatcher(ERROR_PAGE).forward(request,response);
				return;
			}	
	}

}
